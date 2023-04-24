package libratech.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

public class storage {

    private final String localFilePath;
    private final String remoteFilePath;

    public storage(String localFilePath, String remoteFilePath) {
        this.localFilePath = localFilePath;
        this.remoteFilePath = remoteFilePath;
    }

    public String upload() throws IOException {
        String bucketName = "guestify-985ec.appspot.com";
        FileInputStream serviceAccount = new FileInputStream("credentials2.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build();

        Storage storage = storageOptions.getService();

        File file = new File(localFilePath);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        BlobId blobId = BlobId.of(bucketName, remoteFilePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(getMimeType(file))
                .build();
        Blob blob = storage.create(blobInfo, fileContent);
        blob.createAcl(com.google.cloud.storage.Acl.of(com.google.cloud.storage.Acl.User.ofAllUsers(), com.google.cloud.storage.Acl.Role.READER));
        blob.updateAcl(com.google.cloud.storage.Acl.of(com.google.cloud.storage.Acl.User.ofAllUsers(), com.google.cloud.storage.Acl.Role.READER));

        String downloadUrl = blob.getMediaLink();
        String urlWithToken = downloadUrl + "?alt=media&token=" + blob.getGeneratedId();
        String signedUrl = blob.signUrl(1, TimeUnit.MINUTES).toString();

        
        return downloadUrl;
    }

    private String getMimeType(File file) throws IOException {
        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) {
            return "application/octet-stream";
        } else {
            return mimeType;
        }
    }
}
