
package libratech.models;


public class SchoolInfo {
    
    public SchoolInfo() {
        
    }
    
    public String[] getSchoolName() {
        
        String[] schoolNames = {
            "ACLC COLLEGE OF MANDAUE",
            "AMA COMPUTER COLLEGE-CEBU CITY",
            "ASIAN COLLEGE OF TECHNOLOGY",      //have extensions
            "BANTAYAN SOUTHERN INSTITUTE",
            "BAPTIST THEOLOGICAL COLLEGE",
            "BENEDICTO COLLEGE - CEBU CITY CAMPUS",    //have extensions
            "BENEDICTO COLLEGE - MAIN CAMPUS",
            "BENTHEL ASIA SCHOOL OF TECHNOLOGY INC.",
            "CARCAR CITY COLLEGE",
            "CATS AERO COLLEGE",
            "CBD COLLEGE",
            "CEBU INSTITUTE OF TECHNOLOGY UNIVERSITY",
            "CEBU DOCTOR'S UNIVERSITY",         //have extensions
            "CEBU EASTERN COLLEGE",
            "CEBU INSTITUTE OF MEDICINE",
            "CEBU MARY IMMACULATE COLLEGE- TALAMBAN",   //have
            "CNU - BALAMBAN CAMPUS",   //have
            "CNU - MAIN CAMPUS",
            "CNU - MEDELIN CAMPUS",
            "CEBU ROOSEVELT MEMORIAL COLLEGES",
            "CEBU SACRED HEART COLLEGE",    //have
            "CEBU SCHOOL OF MIDWIFERY",
            "CTU - ARGAO CAMPUS", 
            "CTU - BANTAYAN EXTENSION CAMPUS",
            "CTU - BARILI CAMPUS",
            "CTU - CARMEN CAMPUS",
            "CTU - CEBU CITY MOUNTAIN CAMPUS",
            "CTU - DANAO CAMPUS",
            "CTU - DAANBANTAYAN CAMPUS",
            "CTU - DUMANJUG EXTENSION CAMPUS",
            "CTU - GINATILAN EXTENSION CAMPUS",
            "CTU - MAIN CAMPUS",
            "CTU - MALABUYOC EXTENSION CAMPUS",
            "CTU - MOALBOAL CAMPUS",
            "CTU - NAGA EXTENSION CAMPUS",
            "CTU - OSLOB EXTENSION CAMPUS",
            "CTU - PINAMUNGAJAN EXTENSION CAMPUS",
            "CTU - SAN FERNANDO EXTENSION CAMPUS",
            "CTU - SAN FRANCISCO CAMPUS",
            "CTU - SAN REMEGIO EXTENSION CAMPUS",
            "CTU - TABOGON EXTENSION CAMPUS",
            "CTU - TABUELAN EXTENSION CAMPUS",
            "CTU - TUBURAN CAMPUS",
            "CENTRAL PHILIPPINE BIBLE COLLEGE",
            "CENTRE FOR INTERANTIONAL EDUCATION GLOBAL COLLEGES",
            "COLEGIO DE SAN ANTONIO DE PADUA",
            "COLLEGE OF TECHNOLOGICAL SCIENCES-CEBU",
            "COLLEGIUM SOCIETATIS ANGELI PACIS",
            "CONCORD TECHNICAL INSTITUTE",
            "CONSOLACION COMMUNITY COLLEGE",
            "CONSOLATRIX COLLEGE OF TOLEDO CITY, INC.",
            "CORDOVA PUBLIC COLLEGE",
            "DE LA SALLE ANDRES SORAINO MEMORIAL COLLEGE",
            "DON BOSCO TECHNICAL COLLEGE- CEBU, INC.",
            "EVANGELICAL THEOLOGICAL COLLEGE OF THE PHILIPPINES",
            "FELIPE R. VERALLO MEMORIAL FOUNDATION, INC.",
            "GOLDEN SUCCESS COLLEGE",
            "HOLY TRINITY COLLEGE",
            "IMMANUEL BIBLE COLLEGE",
            "INDIANA SCHOOL OF AERONAUTICS",
            "INFORMATICS COLLEGE-CEBU", //have
            "LA CONSOLACION COLLEGE - LILOAN",
            "LAPU-LAPU CITY COLLEGE",
            "LARMEN DE GUIA MEMORIAL COLLEGE",
            "LYCEUM OF CEBU",
            "MADRIDEJOS COMMUNITY COLLEGE",
            "MANDAUE CITY COLLEGE",
            "MARY OUR HELP TECHNICAL INSTITUTE FOR WOMEN CEBU, INC.",
            "MARY'S CHILDREN FORMATION COLLEGE",
            "MATIAS H. AZNAR MEMORIAL COLLEGE OF MEDICINE, INC.",
            "MATILDA L. BRADFORD CHRISTIAN SCHOOL, INC.",
            "MHAM COLLEGE INC.",
            "MICROSYSTEM INTERNATIONAL INSTITUTE OF TECHNOLOGY",
            "MOUNT MORIAH COLLEGE",
            "NORTHEASTERN CEBU COLLEGES",
            "NORTHERN CEBU COLLEGES, INC.",
            "PHILIPPINE STATE COLLEGE OF AERONAUTICS-MACTAN AIR BASE",
            "PROFESSIONAL ACADEMY OF THE PHILIPINES",
            "RIZWOODS",       //have
            "ROGATIONIS SEMINARY COLLEGE-CEBU",
            "ROSEMONT HILLS MONTESSORI COLLEGE",
            "ROYAL CHRISTIAN COLLEGE",
            "SAINT LOUIS COLLEGE-CEBU",
            "SAINT PAUL COLLEGE FOUNDATION INC.",
            "SALAZAR COLLEGES OF SCIENCE AND INSTITTUTE OF TECHNOLOGY",     //have
            "SAN ROQUE COLLEGE DE CEBU - BOGO CAMPUS",    //have
            "SAN ROQUE COLLEGE DE CEBU - CORDOVA CAMPUS",
            "SAN ROQUE COLLEGE DE CEBU - LILOAN CAMPUS",
            "SIBONGA COMMUNITY COLLEGE",
            "SOUTHWESTERN UNIVERSITY - MAIN CAMPUS",      //have
            "SOUTHWESTERN UNIVERSITY - SOUTH CAMPUS",
            "ST. CATHERINES' COLLEGE",
            "ST. CECILIA'S COLLEGE-CEBU, INC.",
            "ST. LOUISE DE MARILLAC COLLEGE OF BOGO",
            "ST. PAUL COLLEGE FOUNDATION-MANDAUE",
            "ST. THERESA'S COLLEGE",
            "STI EDUCATION SERVICES GROUP, INC-STI COLLEGE CEBU",
            "STO. TOMAS COLLEGE, DANAO CITY, INC.",
            "TABOR HILLS COLLEGE - OAD INC.",
            "TALISAY CITY COLLEGE",
            "TRADE-TECH INTERNATION SCIENCE INSTITUTE - COMPOSTELA BRANCH", //have
            "TRADE-TECH INTERNATION SCIENCE INSTITUTE - LILOAN CAMPUS",
            "UC - BANILAD",   //have
            "UC - MAIN",
            "UC - METC",
            "UC - LAPULAPU AND MANDAUE",
            "UC - SCHOOL OF MEDICINE",
            "UC - SCHOOL OF LAW",
            "USC - MAIN CAMPUS", //have
            "USC - NORTH CAMPUS",
            "USC - SOUTH CAMPUS",
            "USC - TALAMBAN CAMPUS",
            "UNIVERSITY OF SAN JOSE-RECOLETOS",
            "UNIVERSITY OF SOUTHERN PHILIPPINES FOUNDATION",    //have
            "UNIVERSITY OF THE PHILIPPINES-CEBU",
            "UV - BANILAD CAMPUS",
            "UV - COMPOSTELA CAMPUS",
            "UV - DANAO CAMPUS",
            "UV - DALAGUETE CAMPUS",
            "UV - LAPU-LAPU CAMPUS",
            "UV - MAIN CAMPUS",
            "UV - MANDAUE CAMPUS",
            "UV - MINGLANILLA CAMPUS",
            "UV - PARDO CAMPUS",
            "UV - TOLEDO CAMPUS",
            
        };
        
        return schoolNames;
    }
    public int[] getSchoolID() {
        
        int[] schoolIDs = {
            403433,
            403297,
            407550, //ACT
            404305,
            00,     //00 means no school id sa excel hehe
            409919, //BENEDICTO COLLEGE - CEBU
            449024,
            448504,
            407677,
            00,
            404483,
            404486,
            406029, //CEBU DOCTOR'S UNIVERSITY
            404485,
            00,
            447510, //CEBU MARY IMMACULATE COLLEGE- TALAMBAN
            00,     //CNU - BALAMBAN
            00,
            00,
            404315,
            404403, //CEBU SACRED HEART COLLEGE
            00,
            406800,     //CTU-ARGAO 
            00,         //"CTU - BANTAYAN EXTENSION CAMPUS",
            00,         //"CTU - BARILI CAMPUS",
            600265,     //"CTU - CARMEN CAMPUS",
            00,         //"CTU - CEBU CITY MOUNTAIN CAMPUS",
            600133,     //"CTU - DANAO CAMPUS",
            406813,     //"CTU - DAANBANTAYAN CAMPUS",
            00,         //"CTU - DUMANJUG EXTENSION CAMPUS",
            00,         //"CTU - GINATILAN EXTENSION CAMPUS",
            406799,     //"CTU - MAIN CAMPUS",
            00,         //"CTU - MALABUYOC EXTENSION CAMPUS",
            406819,     //"CTU - MOALBOAL CAMPUS",
            00,         //"CTU - NAGA EXTENSION CAMPUS",
            00,         //"CTU - OSLOB EXTENSION CAMPUS",
            00,         //"CTU - PINAMUNGAJAN EXTENSION CAMPUS",
            00,         //"CTU - SAN FERNANDO EXTENSION CAMPUS",
            00,         //"CTU - SAN FRANCISCO CAMPUS",
            00,         //"CTU - SAN REMEGIO EXTENSION CAMPUS",
            00,         //"CTU - TABOGON EXTENSION CAMPUS",
            00,         //"CTU - TABUELAN EXTENSION CAMPUS",
            00,         //"CTU - TUBURAN CAMPUS",
            00,
            00,
            405946,
            403323,
            00,
            404492,
            600259,
            404639,
            00,
            404637,
            404493,
            404491,
            404316,
            447534,
            407542,
            00,
            00,
            403330, //INFORMATICS COLLEGE-CEBU
            404355,
            00,
            448633,
            407025,
            403283,
            600261,
            447145,
            403284,
            00,
            404449,
            00,
            409857,
            00,
            491502,
            404317,
            00,
            447092,
            2448557,    //RIZWOODS
            403342,
            491507,
            407635,
            404585,
            405926,
            1447068,    //SALAZAR COLLEGES OF SCIENCE AND INSTITTUTE OF TECHNOLOGY
            447030,    //SAN ROQUE COLLEGE DE CEBU - BOGO CAMPUS
            447044,
            404358,
            00,
            404462,    //SOUTHWESTERN UNIVERSITY - MAIN CAMPUS
            404510,
            404324,
            404369,
            404318,
            447552,
            404465,
            403349,
            409114,
            410121,
            00,
            409686,    //TRADE-TECH INTERNATION SCIENCE INSTITUTE - COMPOSTELA BRANCH
            407445,
            403351,    //UC - BANILAD
            403355,
            447181,
            404587,
            00,
            00,
            00,         //USC - MAIN CAMPUS
            407546,
            00,
            00,
            447087,
            00,         //UNIVERSITY OF SOUTHERN PHILIPPINES FOUNDATION
            600332,
            00,         //UV - BANILAD CAMPUS
            00,
            00,
            00,
            00,
            00,
            00,
            00,
            00,
            

        };
        
        return schoolIDs;
    }
}
