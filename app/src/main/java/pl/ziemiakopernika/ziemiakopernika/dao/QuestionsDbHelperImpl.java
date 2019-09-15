package pl.ziemiakopernika.ziemiakopernika.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pl.ziemiakopernika.ziemiakopernika.dao.QuestionsContract.*;
import pl.ziemiakopernika.ziemiakopernika.model.Question;

public class QuestionsDbHelperImpl extends SQLiteOpenHelper implements QuestionsDbHelper {

    private static final String DATABASE_NAME = "ZiemiaKopernikaDatabse.db";
    private static final int DATABASE_VERSION = 24;

    private SQLiteDatabase database;

    public QuestionsDbHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.database = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.TYPE_OF_QUESTION + " INTEGER, " +
                QuestionsTable.QUESTION + " TEXT, " +
                QuestionsTable.ANSWER_ONE + " TEXT, " +
                QuestionsTable.ANSWER_TWO + " TEXT, " +
                QuestionsTable.ANSWER_THREE + " TEXT, " +
                QuestionsTable.ANSWER_FOUR + " TEXT, " +
                QuestionsTable.SHOWED_TIMES + " INTEGER" + ")";
        database.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable(){
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Merkury", "Wenus", "Ziemia", "Mars"));
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Merkury", "Jowisz", "Uran", "Neptun"));
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Wenus", "Ziemia", "Jowisz", "Neptun"));
        addQuestion(new Question(1, "Uporządkuj planety od najbliżej znajdującej się słońca", "Mars", "Jowisz", "Saturn", "Uran"));
        addQuestion(new Question(0, "W skład Układu Słonecznego NIE wchodzą", "Gwiazda Centauri", "Planetoidy", "Asteroidy", "Planety"));
        addQuestion(new Question(0, "Jednostka astronomiczna jest równa odległości", "Ziemi od Słońca", "Ziemi od Księżyca", "Księżyca od Słońca", "Najbliższej planety od Słońca"));
        addQuestion(new Question(0, "Ziemia wykonuje ruch", "Obiegowy i obrotowy", "Obrotowy", "Nie porusza się", "Obiegowy"));
        addQuestion(new Question(0, "Ruch obiegowy Ziemi trwa", "12 miesięcy", "12 godzin", "24 godziny", "Miesiąc"));
        addQuestion(new Question(0, "Ruch obrotowy Ziemi trwa", "24 godziny", "12 godzin", "12 miesięcy", "Miesiąc"));
        addQuestion(new Question(0, "Księżyc jest", "Naturalnym satelitą Ziemi", "Sztucznym satelitą Marsa", "Naturalnym satelitą Marsa", "Sztucznym satelitą Ziemi"));
        addQuestion(new Question(0, "Prawo naturalnego ciążenia dotyczy", "Wszystkich ciał materialnych", "Słońca i Ziemi", "Słońca i Księżyca", "Tylko obiektów astronomicznych"));
        addQuestion(new Question(0, "Prędkość planet wokół Slońca", "Rośnie i maleje na przemian", "Stale rośnie", "Stale maleje", "Jest stała"));
        addQuestion(new Question(0, "Siły powszechnego ciążenia zależą", "Tylko od masy i odległości", "Tylko od masy", "Tylko od odległości między ciałami", "Nie zależą od masy i odległości"));
        addQuestion(new Question(0, "Pełnia Księżyca zdarza się", "Co miesiąc", "Co tydzień", "Co kwartał", "Co rok"));
        addQuestion(new Question(0, "Na satelitę geostacjonarnego", "Działa siła wzdłuż promienia orbity", "Nie działa żadna siła", "Działa siła w kierunku ruchu", "Działa siła prostopadła do promienia orbity"));
        addQuestion(new Question(0, "Ile istnieje wokół Ziemi orbit stacjonarnych?", "Jedna", "Kilka", "Kilkanaście", "Nie ma takich orbit"));
        addQuestion(new Question(0, "Zaćmienie Księżyca zachodzi, gdy Księżyc jest", "W pełni", "W nowiu", "W pierwszym kwadrze", "W drugim kwadrze"));
        addQuestion(new Question(0, "Teoria heliocentryczna NIE zakłada, że", "Ziemia jest nieruchoma", "Słońce jest nieruchome", "Planety krążą wokół Słońca", "Żadna z odpowiedzi nie jest poprawna"));
        addQuestion(new Question(0, "Ile gwiazd jest w Układzie Słonecznym?", "Jedna", "Nie ma żadnej", "Osiem", "Dziewięć"));
        addQuestion(new Question(0, "Ile planet jest w Układzie Słonecznym?", "Osiem", "Dziewięć", "Siedem", "Dziesięć"));
        addQuestion(new Question(0, "Jaka jest największa planeta Układu Słonecznego?", "Jowisz", "Słońce", "Saturn", "Uran"));
        addQuestion(new Question(0, "Jaka jest najmniejsza planeta Układu Słonecznego?", "Merkury", "Mars", "Neptun", "Uran"));
        addQuestion(new Question(0, "Która planeta w naszym układzie jest najgorętsza?", "Merkury", "Mars", "Wenus", "Ziemia"));
        addQuestion(new Question(0, "Która planeta w naszym układzie jest najzimniejsza?", "Uran", "Neptun", "Saturn", "Mars"));
        addQuestion(new Question(0, "Uważa się, że wewnątrz tej planety powstają diamenty. Jaka to planeta?", "Neptun", "Żadna z wymienionych", "Jowisz", "Saturn"));
        addQuestion(new Question(0, "Ile księżycy ma Uran?", "27", "24", "36", "29"));
        addQuestion(new Question(0, "Ile pierścieni ma Uran?", "13", "Żadnych", "14", "17"));
        addQuestion(new Question(0, "W którym roku odkryto planetę Uran?", "1781", "1652", "1651", "1740"));
        addQuestion(new Question(0, "W którym roku odkryto planetę Neptun?", "1846", "1862", "1894", "1901"));
        addQuestion(new Question(0, "Ile pierścieni ma Saturn?", "9", "12", "13", "10"));
        addQuestion(new Question(0, "Ile księżycy ma Jowisz?", "67", "69", "70", "63"));
        addQuestion(new Question(0, "Ile księżycy ma Saturn?", "62", "58", "67", "64"));
        addQuestion(new Question(0, "Ile księżycy ma Neptun?", "14", "18", "11", "17"));
        addQuestion(new Question(0, "Która planeta nie ma żadnego księżyca?", "Merkury", "Mars", "Neptun", "Uran"));
        addQuestion(new Question(0, "Która z planet NIE jest kamienista?", "Jowisz", "Merkury", "Wenus", "Mars"));
        addQuestion(new Question(0, "W jakim czasie dociera światło ze Słońca do Ziemi?", "8 minut", "21 minut", "18 minut", "24 minut"));
        addQuestion(new Question(0, "Czym jest pluton?", "Planetą karłowatą", "Kwazarem", "Gwiazdą", "Satelitą naturalnym"));
        addQuestion(new Question(0, "Która z gwiazd znajduje się najbliżej Ziemi?", "Słońce", "Syriusz", "Altair", "Betelgeza"));
        addQuestion(new Question(0, "Parsek, złożenie pochodzące od słów paralaka i sekunda, to", "Jednostka odległości używana w astronomii", "Element kombinezonu kosmonauty", "Część statku kosmicznego", "Część statku kosmicznego"));
        addQuestion(new Question(0, "Rok świetlny to w astronomii jednostka", "Odległości", "Czasu", "Masy", "Siły"));
        addQuestion(new Question(0, "Kto był twórcą teorii heliocentrycznej?", "Mikołaj Kopernik", "Jan Heweliusz", "Galileusz", "Ptolemeusz"));
        addQuestion(new Question(0, "Z jakiego kalendarza korzystamy w Polsce?", "Z gregoriańskiego", "Z juliańskiego", "Ze zwrotnikowego", "Z równikowego"));
        addQuestion(new Question(0, "Kiedy możemy obserwować całkowite zaćmienie Słońca?", "Gdy Księżyc znajdzie się pomiędzy Słońcem a Ziemią", "Co roku w lecie", "Gdy Ziemia znajduje się między Słońcem a Księżycem", "Gdy wszystkie planety ułożą się w jednej linii"));
        addQuestion(new Question(0, "Od czego nazwano planety: Merkury, Wenus, Mars, Jowisz i Saturn?", "Od imion mitologicznych bogów", "Od imion wynalazców", "Od ich kolorów", "Od tego, z czego są zbudowane"));
        addQuestion(new Question(0, "Ile wynosi rekord prędkości wiatru?", "2100 km/h", "1900 km/h", "1700 km/h", "15000 km/h"));
        addQuestion(new Question(0, "Która z wymienionych planet zalicza się do planet olbrzymów?", "Uran", "Merkury", "Wenus", "Ziemia"));
        addQuestion(new Question(0, "Gdzie można znaleźć Wielką Czerwoną Plamę?", "Na Jowiszu", "Na Marsie", "Na Merkurym", "Na Saturnie"));
        addQuestion(new Question(0, "Gdzie można znaleźć Wielką Ciemną Plamę?", "Na Neptunie", "Na Uranie", "Na Saturnie", "Na Jowiszu"));
        addQuestion(new Question(0, "Jak powstała nazwa planeta?", "Od greckiego słowa oznaczającego wędrowca", "Od greckiego słowa oznaczającego kulę", "Od greckiego słowa oznaczającego skałę", "Od greckiego słowa oznaczającego podróżnika"));
        addQuestion(new Question(0, "Jak nazywa się najbliższa, oprócz Słońca, gwiazda Ziemi?", "Proxima Centauri", "Rigel", "Polaris", "Arktur"));

        addQuestion(new Question(0, "Kto jako pierwszy mówił o kulistości Ziemi?", "Pitagoras", "Arystoteles", "Starożytni Grecy", "Starożytni Rzymianie"));
        addQuestion(new Question(0, "Na której z wymienionych planet nie występuje zjawisko zorzy polarnej?", "Mars", "Jowisz", "Saturn", "Uran"));
        addQuestion(new Question(0, "Kto jako pierwszy stanął na księżycu?", "Neil Armstrong", "Steven Baker", "Mike Adams", "Harrison Schmitt"));
        addQuestion(new Question(1, "Kto jako pierwszy stanął na księżycu? Uporządkuj od pierwszej do ostatniej osoby", "Neil A. Armstrong", "Edwin E. Aldrin", "Charles P. Conrad", "Alan L. Bean"));
        addQuestion(new Question(1, "Kto jako pierwszy stanął na księżycu? Uporządkuj od pierwszej do ostatniej osoby", "Neil A. Armstrong", "Alan B. Shepard", "David R. Scott", "John W. Young"));
        addQuestion(new Question(0, "Ile wynosi średnia temperatura w przestrzeni kosmicznej?", "-270 stopni Celsjusza", "-250 stopni celsjusza", "-140 stopni celsjusza", "Nie da się tego ustalić"));
        addQuestion(new Question(0, "Jak nazywa się księżyc Plutona?", "Charon", "Chiron", "Cheron", "Pluton nie ma księżyca"));
        addQuestion(new Question(0, "Jak nazywa się księżyc planety Merkury?", "Merkury nie ma księżyca", "Charon", "Kalisto", "Ganimedes"));
        addQuestion(new Question(0, "Pierwsza Kobieta kosmonautka to", "Walentina Tierieszkowa", "Polina Gagarina", "Swietłana Sawicka", "Sally Ride"));
        addQuestion(new Question(0, "Nazwy planet Układu Słonecznego zostały zaczerpnięte z", "Mitologii rzymskiej", "Mitologii greckiej", "Mitologii nordyckiej", "Nazewnictwa pierwiastków chemicznych"));
        addQuestion(new Question(0, "Jak nazywała się pierwsza sonda międzyplanetarna? ", "Łuna-1", "Sputnik", "Celuton 2000", "Star-1"));
        addQuestion(new Question(0, "Teoria stworzona przez Mikołaja Kopernika nazywa się", "Heliocentryczna", "Teocentryczna", "Geocentryczna", "Antropocentryczna"));
        addQuestion(new Question(0, "Jak nazywa się przejście Wenus na tle tarczy Słońca?", "Tranzyt", "Fugam", "Zakryt", "Przejście"));
        addQuestion(new Question(0, "Co to koniunkcja?", "Ustawienie się kilku lub więcej planet w jednej linii", "Zderzenie dwóch galaktyk", "Zderzenie planety z asteroidą", "Inna nazwa zaćmienia"));
        addQuestion(new Question(0, "Jak nazywa się łazik wysłany na Marsa w 2011 roku?", "Curiosity", "ExoMars", "Rover", "Sojourner"));
        addQuestion(new Question(0, "Którą planetę zaobserwowano dopiero w XV wieku?", "Neptun", "Saturn", "Merkury", "Jowisz"));
        addQuestion(new Question(0, "Gwiazdy mają różne kolory, które oznaczają temperaturę. Jaki kolor mają najgorętsze z nich?", "Niebieski", "Żółty", "Biały", "Czerwony"));
        addQuestion(new Question(0, "Jak nazywa się największy meteoryt jaki znaleziono na Ziemi?", "Hoba", "Armanty", "Selene", "Mobsi"));
        addQuestion(new Question(0, "Ile waży największy meteoryt jaki znaleziono na Ziemi?", "60 ton", "40 ton", "80 ton", "105 ton"));
        addQuestion(new Question(0, "Który księżyc jest największy w Układzie Słonecznym?", "Ganimedes", "Tytan", "Europa", "Anakin"));
        addQuestion(new Question(0, "Którego pierwiastka jest najwięcej w fotosferze Słońca?", "Wodoru", "Helu", "Węgla", "Argonu"));
        addQuestion(new Question(0, "XVII-wieczny niemiecki astronom i astrolog. Jego najbardziej znane dzieła to: Astronomia nova i Epitome astronomiae Copernicanae - kto to? ", "Johannes Kepler", "Mikołaj Kopernik", "Edwin Hubble", "Tycho Brahe"));
        addQuestion(new Question(0, "Co oznacza jednostka astronomiczna AU?", "Jest to średnia odległość między Słońcem a Ziemią", "Jest to inaczej rok świetlny", "Nie ma takiej jednostki", "Jest to prędkość z jaką porusza się Droga Mleczna"));
        addQuestion(new Question(0, "Co oznacza jednostka astronomiczna LY?", "Nie ma takiej jednostki", "Jest to inaczej rok świetlny", "Jest to średnia odległość między Słońcem a Ziemią", "Jest to prędkość z jaką porusza się Droga Mleczna"));
        addQuestion(new Question(0, "Ile wynosi wartość AU dla Neptuna?", "30.01", "9.54", "19.31", "47.07"));
        addQuestion(new Question(0, "Czym jest \"Halo\"?", "Materią wokół środka galaktyki", "Gwiazdą", "Systemem komunikacji", "Dużą, kulistą chmurą komet otaczająca cały Układ Słoneczny"));
        addQuestion(new Question(0, "Z czego zbudowane są komety?", "Głównie z lodu i skał", "Głównie z ciekłej lawy i skał", "Głównie z gazów", "Głównie z lodu i gazów"));
        addQuestion(new Question(0, "Co ile dni Słońce wykonuje pełną rotację?", "25-35", "5-15", "15-25", "35-45"));
        addQuestion(new Question(0, "Na jakiej planecie występuje najwięcej wulkanów?", "Wenus", "Ziemia", "Mars", "Jowisz"));
        addQuestion(new Question(0, "Ile trwa dzień na plutonie?", "6 dni i 9 godzin", "5 dni i 9 godzin", "6 dni i 13 godzin", "5 dni i 13 godzin"));
        addQuestion(new Question(0, "Jest niemal identyczna pod względem wielkości z Ziemią. Ich promień różni się zaledwie o 323 kilometry. Jaka to planeta?", "Wenus", "Mars", "Uran", "Merkury"));
        addQuestion(new Question(1, "Ułóż planety od najmniejszej do największej", "Merkury", "Mars", "Wenus", "Ziemia"));
        addQuestion(new Question(1, "Ułóż planety od najmniejszej do największej", "Wenus", "Ziemia", "Neptun", "Uran"));
        addQuestion(new Question(1, "Ułóż planety od najmniejszej do największej", "Neptun", "Uran", "Saturn", "Jowisz"));
        addQuestion(new Question(0, "Jowisz to największa planeta Układu Słonecznego. Jego masa jest 2,5 razy większa od", "Wszystkich pozostałych planet razem", "Saturna", "Słońca", "Uranu"));
        addQuestion(new Question(0, "Posiada największa ilość kraterów w Układzie Słonecznym oraz ciemne i jasne strefy. Jaka to planeta?", "Merkury", "Wenus", "Mars", "Jowisz"));
        addQuestion(new Question(0, "Jakie planety są nazywane wewnętrznymi?", "Takie, które są najbliżej słońca", "Takie, które krążą wokół gwiazdy", "Takie, które są zbudowane ze skał", "Żadna z odpowiedzi nie jest poprawna"));
        addQuestion(new Question(0, "Z czego zbudowane jest Słońce?", "Wszystkie odpowiedzi są poprawne", "Z wodoru", "Z helu", "Z węgla"));
        addQuestion(new Question(0, "Czyjej planety skały możemy znaleźć na Ziemi?", "Marsa", "Merkurego", "Jowisza", "Każda odpowiedź jest poprawna"));
        addQuestion(new Question(0, "Jak długo trwał lot misji Apollo 11, zanim dotarła ona na Księżyc?", "3 dni", "1 dzień", "2 dni", "4 dni"));
        addQuestion(new Question(0, "Które obiekty we wszechświecie świecą najjaśniej?", "Kwazary", "Supernowe", "Nowe", "Gwiazdy supergiganty"));
        addQuestion(new Question(0, "Na których planetach Układu Słonecznego występują zorze?", "Na Ziemi, Jowiszu, Saturnie, Uranie i Neptunie", "Tylko na Ziemi", "Na wszystkich", "Na Ziemi, Merkurym, Wenus i Marsie"));
        addQuestion(new Question(0, "W 1973 NASA wysłała w kosmos Anitę i Arabellę. Były to..?", "Samice pająka krzyżaka", "Myszki polne", "Szympanse", "Rybki akwariowe"));
        addQuestion(new Question(0, "Jaką prędkość musi osiągnąć rakieta, by mogła osiągnąć przestrzeń kosmiczną?", "vI = 7,91 km/s", "vII = 11,19 km/s", "vIII = 16,7 km/s", "Zależy od masy rakiety"));
        addQuestion(new Question(0, "Jak miał na imię pierwszy szympans który znalazł się na orbicie ziemskiej?", "Enos", "Berg", "Ham", "Washoe"));
        addQuestion(new Question(0, "Jak nazywał się pojazd kosmiczny, w którym zginęło pierwszych trzech kosmonautów?", "Apollo 1", "Challenger", "Sojuz 1", "Columbia"));
        addQuestion(new Question(0, "Zamontowania czego domagali się pierwsi amerykańscy astronauci?", "Okna", "Katapulty ratunkowej", "Dodatkowego spadochronu", "Włazu bezpieczeństwa"));
        addQuestion(new Question(0, "Co w Rosji tradycyjnie musi zrobić kosmonauta zanim wyleci w kosmos?", "Zasadzić drzewo", "Pozdrowić władzę na Kremlu", "Stanąć na rękach", "Zaśpiewać hymn"));
        addQuestion(new Question(0, "Naukowcy odkryli, że na Uranie i Neptunie występują deszcze", "Diamentowe", "Metanowe", "Siarkowe", "Wodorowe"));
        addQuestion(new Question(0, "Czyimi imionami są nazwy księżyców Urana, jak Ariel, Umbriel, Tytania, Oberon i Miranda?", "Bohaterów sztuk Szekspira", "Bogów rzymskich", "Postaci z legend arturiańskich", "Cesarzy rzymskich i ich żon"));

        addQuestion(new Question(0, "Ile wyróżniamy praw Keplera?", "Trzy", "Jedno", "Dwa", "Cztery"));
        addQuestion(new Question(0, "W którym roku wydano dzieło \"O obrotach sfer niebieskich\"?", "1543", "1521", "1566", "1511"));
        addQuestion(new Question(0, "Kto wydał wielkie dzieło \"O obrotach sfer niebieskich\"?", "Mikołaj Kopernik", "Johannes Kepler", "Izaak Newton", "Galileo Galilei"));
        addQuestion(new Question(0, "Przez około 1400 lat poprzedzających rewolucję kopernikańską panowała geocentryczna teoria..", "Ptolemeusza", "Pitagorasa", "Arystotelesa", "Talesa"));
        addQuestion(new Question(0, "\"Nic piękniejszego nad niebo, które przecież ogarnia wszystko, co piękne\" - kto jest autorem tych słów?", "Mikołaj Kopernik", "Izaak Newton", "Johannes Keplera", "Stephen Hawking"));
        addQuestion(new Question(0, "\"Próżnowaniem poniża się dary natury\" - kto jest autorem tych słów?", "Mikołaj Kopernik", "Izaak Newton", "Johannes Keplera", "Stephen Hawking"));
        addQuestion(new Question(0, "O Mikołaju Koperniku mówi się, że..", "Wstrzymał Słońce, ruszył Ziemię", "Wstrzymał Słońce, wstrzymał Ziemię", "Wstrzymał Ziemię, ruszył Słońce", "Ruszył Ziemię, ruszył Słońce"));
        addQuestion(new Question(0, "\"Co my wiemy, to tylko kropelka. Czego nie wiemy, to cały ocean\" - kto jest autorem tych słów?", "Izaak Newton", "Mikołaj Kopernik", "Johannes Kepler", "Stephen Hawking"));
        addQuestion(new Question(0, "\"Odkryte przeze mnie prawa są bardzo proste. Formułowałem je szybko, ale przedtem bardzo długo myślałem\" - kto jest autorem tych słów?", "Izaak Newton", "Mikołaj Kopernik", "Johannes Kepler", "Stephen Hawking"));
        addQuestion(new Question(0, "\"Bóg jest matematykiem\" - kto jest autorem tych słów?", "Johannes Kepler", "Mikołaj Kopernik", "Izaak Newton", "Stephen Hawking"));
        addQuestion(new Question(0, "\"Radość jest potrzebą, siłą i wartością życia\" - kto jest autorem tych słów?", "Johannes Kepler", "Mikołaj Kopernik", "Izaak Newton", "Stephen Hawking"));
        addQuestion(new Question(0, "Kto sformułował prawo powszechnej grawitacji?", "Izaak Newton", "Mikołaj Kopernik", "Johannes Hepler", "Nikt z nich"));
        addQuestion(new Question(0, "Był pierwszym uczonym, który rozumiał rolę doświadczenia w badaniu zjawisk przyrody i wykonywał eksperymenty fizyczne. Kto to?", "Galileusz", "Pitagoras", "Arystoteles", "Tales"));
        addQuestion(new Question(0, "Spadanie swobodne to ruch ciała, na które..", "Działa tylko siła grawitacji", "Nie działa żadna siła", "Działa siła grawitacji i opór powietrza", "Działa tylko opór powietrza"));
        addQuestion(new Question(0, "Co spadnie szybciej w próżni - ruda miedzi czy węgla?", "Spadną w takim samym czasie", "Nie da sie tego ustalić", "Ruda miedzi", "Ruda węgla"));
        addQuestion(new Question(0, "W którym roku została założona organizacja NASA?", "1958", "1889", "1934", "1979"));
        addQuestion(new Question(0, "Czym jest albedo?", "Stosunek ilości promieniowania odbitego do padającego", "Stosunek masy do objętości planety", "Stosunek powierzchni do masy planety", "Stosunek powierzchni do objętości planety"));
        addQuestion(new Question(0, "Która planeta w Układzie Słonecznym ma najwyższe albedo?", "Wenus", "Merkury", "Jowisz", "Ziemia"));
        addQuestion(new Question(0, "Co w większości ochrania Ziemię przed wiatrem słonecznym?", "Pole magnetyczne", "Heliosfera", "Atmosfera", "Grawitacja Jowisza"));
        addQuestion(new Question(0, "Która sonda kosmiczna znajduje się obecnie najdalej Ziemi?", "Voyager 1", "Rosetta", "New Horizons", "Genesis"));
        addQuestion(new Question(0, "Która planeta ma wsteczną rotację niż pozostałe planety Układu Słonecznego?", "Wenus", "Uran", "Ziemia", "Jowisz"));
        addQuestion(new Question(0, "Czy znajdziemy na asteroidach wodę?", "Tylko w stanie stałym", "Tylko w stanie ciekłym", "Nie", "Tak, we wszystkich stanach"));
        addQuestion(new Question(0, "Ilu astronautów uczestniczyło w pechowej misji Apollo 13?", "3", "4", "6", "5"));
        addQuestion(new Question(0, "Od którego roku na północnym biegunie Saturna trwa nieprzerwanie dzień?", "Od 2007", "Od 2015", "Od 1993", "Od 1997"));
        addQuestion(new Question(0, "Czym jest Kasjopeja", "Gwiazdozbiorem", "Gwiazdą", "Planetoidem", "Kometą"));
        addQuestion(new Question(0, "Ile jest stwierdzonych przypuszczalnych czarnych dziur w centrum naszej galaktyki?", "Około 10 000", "Około 3", "Około 100", "Tylko jedna"));
        addQuestion(new Question(0, "Od którego roku działa Planetarium w Toruniu?", "1994", "2002", "1950", "1989"));
        addQuestion(new Question(0, "W której planecie pole magnetyczne tworzy się z ruchów jądra wewnętrznego?", "Ziemia", "Wenus", "Neptun", "Jowisz"));
        addQuestion(new Question(0, "Na ilu planetach istnieje zjawisko zorzy planetarnej?", "Na pięciu", "Na siedmiu", "Tylko na jednej", "Na trzech"));
        addQuestion(new Question(0, "Czym jest Astrolabium?", "Przyrządem astronomicznym używanym w nawigacji", "Czasem astralnym", "Coroczną nagrodą dla astronomów", "Niezbadaną przestrzenią kosmiczną"));
        addQuestion(new Question(0, "Który astronom jest znany z opisania oraz zrobienia dokładnych map Księżyca?", "Jan Heweliusz", "Edmund Halley", "Ludwik Pasteur", "Bohdan Paczyński"));
        addQuestion(new Question(0, "Przed czym chroni nas pole magnetyczne?", "Przed promieniowaniem", "Przed meteorami", "Przed gorącem", "Przed asteroidami"));
        addQuestion(new Question(0, "Czym jest \"spadająca gwiazda\"?", "Okruchem skalnym", "Zepsutym satelitą", "Fragmentem atmosfery", "Wszystkie odpowiedzi są niepoprawne"));
        addQuestion(new Question(0, "W którym roku założono Polskie Towarzystwo Astronomiczne?", "1923", "2014", "1951", "1970"));
        addQuestion(new Question(0, "Ile czasu trwa pełen obrót Wenus (wokół wlasnej osi)?", "243 dni ziemskich", "115 dni ziemskich", "161 dni ziemskich", "41 dni ziemskich"));
        addQuestion(new Question(0, "Ile razy w przybliżeniu średnica Jowisza jest większa niż Ziemi?", "11", "4", "2", "7"));
        addQuestion(new Question(0, "Kto opracował metodę pozwalającą odkrywać planety pozasłoneczne?", "Bohdan Paczyński", "Mikołaj Kopernik", "Aleksander Wolszczan", "Mirosław Hermaszewski"));
        addQuestion(new Question(0, "Gdzie możemy znaleźć Ocean Burz?", "Na Księżycu", "Na Marsie", "Na Merkurym", "Na Wenusie"));
        addQuestion(new Question(0, "Co jest Gwiazdą Zaranną?", "Wenus", "Słońce", "Mars", "Proxima Centauri"));
        addQuestion(new Question(0, "Gdzie występuje promieniowanie Hawkinga?", "W czarnej dziurze", "W mgławicach", "W gwiazdach neutronowych", "W pulsarach"));
        addQuestion(new Question(0, "Masa której planety była źle opisana przez co krążyly spekulacje o kolejnej planecie?", "Neptuna", "Urana", "Jowisza", "Marsa"));
        addQuestion(new Question(0, "W której misji na Księżyc usłyszeliśmy słynne słowa \"Houston we have a problem\"?", "Apollo 13", "Apollo 11", "Apollo 12", "Apollo 14"));
        addQuestion(new Question(0, "Jak nazywał się ostatni człowiek na Księżycu?", "Eugene Cernan", "Neil Armstrong", "Virgil Grissom", "Alan Sherpard"));
        addQuestion(new Question(0, "Jak nazywała się sonda która jako pierwsza dotarła do Urana i Neptuna?", "Voyager 2", "New Horizons", "Voyager 1", "Giotto"));
        addQuestion(new Question(0, "Ile lat trwała misja sondy Cassini?", "20", "10", "25", "15"));
        addQuestion(new Question(1, "Które kraje miały najwcześniej swoich astronautów w kosmosie? Uporządkuj", "ZSRR", "Stany Zjednoczone", "Czechosłowacja", "Polska"));
        addQuestion(new Question(1, "Które kraje miały najwcześniej swoich astronautów w kosmosie? Uporządkuj", "Polska", "NRD", "Bułgaria", "Węgry"));
        addQuestion(new Question(1, "Które kraje miały najwcześniej swoich astronautów w kosmosie? Uporządkuj", "ZSRR", "Czechosłowacja", "NRD", "Węgry"));
        addQuestion(new Question(0, "Jakie katastrofy naturalne występują na Księżycu?", "Trzęsienia gruntu", "Trąby powietrzne", "Powodzie", "Burze pyłowe"));
        addQuestion(new Question(0, "Jak nazywa się największa odkryta galaktyka?", "IC 1101", "M 82", "NGC 234", "IC 879"));
    }

    private void addQuestion(Question question){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.TYPE_OF_QUESTION, question.getTypeOfQuestion());
        contentValues.put(QuestionsTable.QUESTION, question.getQuestion());
        contentValues.put(QuestionsTable.ANSWER_ONE, question.getAnswerOne());
        contentValues.put(QuestionsTable.ANSWER_TWO, question.getAnswerTwo());
        contentValues.put(QuestionsTable.ANSWER_THREE, question.getAnswerThree());
        contentValues.put(QuestionsTable.ANSWER_FOUR, question.getAnswerFour());
        contentValues.put(QuestionsTable.SHOWED_TIMES, question.getShowedTimes());
        database.insert(QuestionsTable.TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.database = sqLiteDatabase;
        database.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(database);
    }

    @Override
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionsList = new ArrayList<>();
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setTypeOfQuestion(cursor.getInt(cursor.getColumnIndex(QuestionsTable.TYPE_OF_QUESTION)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.QUESTION)));
                question.setAnswerOne(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_ONE)));
                question.setAnswerTwo(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_TWO)));
                question.setAnswerThree(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_THREE)));
                question.setAnswerFour(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_FOUR)));
                question.setShowedTimes(cursor.getInt(cursor.getColumnIndex(QuestionsTable.SHOWED_TIMES)));
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionsTable._ID)));
                questionsList.add(question);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }

    @Override
    public void addShowedTimeToSongWithID(int id) {
        Question targetQuestion = new Question();
        for(Question question: getAllQuestions()) {
            if (question.getId() == id)
                targetQuestion = question;
        }
        database.execSQL("UPDATE " + QuestionsTable.TABLE_NAME + " SET "
                + QuestionsTable.SHOWED_TIMES + " = " + (targetQuestion.getShowedTimes()+1) + " WHERE "
                + QuestionsTable._ID + " = " + id);
    }

    @Override
    public int getMinShowedTimes() {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT MIN(" + QuestionsTable.SHOWED_TIMES + ") FROM " + QuestionsTable.TABLE_NAME, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    @Override
    public ArrayList<Question> getQuestionsByShowedTimes(int number) {
        ArrayList<Question> questionsList = new ArrayList<>();
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE "
                        + QuestionsTable.SHOWED_TIMES + " = " + number, null);
        if(cursor.moveToFirst()){
            do{
                Question question = new Question();
                question.setTypeOfQuestion(cursor.getInt(cursor.getColumnIndex(QuestionsTable.TYPE_OF_QUESTION)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.QUESTION)));
                question.setAnswerOne(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_ONE)));
                question.setAnswerTwo(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_TWO)));
                question.setAnswerThree(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_THREE)));
                question.setAnswerFour(cursor.getString(cursor.getColumnIndex(QuestionsTable.ANSWER_FOUR)));
                question.setShowedTimes(cursor.getInt(cursor.getColumnIndex(QuestionsTable.SHOWED_TIMES)));
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionsTable._ID)));
                questionsList.add(question);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }
}
