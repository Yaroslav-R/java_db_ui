package GenerateValue;


import TableEntity.*;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Class for generation values to table
 * @author YarSlv3
 * @version 1.0
 */
public class GeneratorOfValues {
    /**
     * @value Массив мужских имён
     */
    private static final String[] male_first_name = {"Александр","Алексей","Альберт","Анатолий","Андрей","Антон","Аркадий","Арсений","Артём","Борис","Вадим","Валентин","Валерий","Василий","Виктор","Виталий","Владимир","Владислав","Вячеслав","Геннадий","Георгий","Герман","Григорий","Даниил","Денис","Дмитрий","Евгений","Егор","Иван","Игнатий","Игорь","Илья","Константин","Лаврентий","Леонид","Лука","Макар","Максим","Матвей","Михаил","Никита","Николай","Олег","Роман","Семён","Сергей","Станислав","Степан","Фёдор","Эдуард","Юрий","Ярослав"};
    /**
     * @vallue Массив мужских отвеств
     */
    private static final String[] male_middle_name = {"Александрович","Алексеевич","Альбертович","Анатольевич","Андреевич","Антонович","Аркадьевич","Арсеньевич","Артёмович","Борисович","Вадимович","Валентинович","Валерьевич","Васильевич","Викторович","Витальевич","Владимирович","Владиславович","Вячеславович","Геннадьевич","Георгиевич","Германович","Григорьевич","Даниилович","Денисович","Дмитриевич","Евгеньевич","Егорович","Иванович","Игнатьевич","Игоревич","Ильич","Константинович","Лаврентьевич","Леонидович","Лукич","Макарович","Максимович","Матвеевич","Михайлович","Никитич","Николаевич","Олегович","Романович","Семёнович","Сергеевич","Станиславович","Степанович","Фёдорович","Эдуардович","Юрьевич","Ярославович"};
    private static final String[] male_last_name = {"Смирнов","Иванов","Кузнецов","Попов","Соколов","Лебедев","Козлов","Новиков","Морозов","Петров","Волков","Соловьев","Васильев","Зайцев","Павлов","Семенов","Голубев","Виноградов","Богданов","Воробьев","Федоров","Михайлов","Беляев","Тарасов","Белов","Комаров","Орлов","Киселев","Макаров","Андреев","Ковалев","Ильин","Гусев","Титов","Кузьмин","Кудрявцев","Баранов","Куликов","Алексеев","Степанов","Яковлев","Сорокин","Сергеев","Романов","Захаров","Борисов","Королев","Герасимов","Пономарев","Григорьев","Лазарев","Медведев","Ершов","Никитин","Соболев","Рябов","Поляков","Цветков","Данилов","Жуков","Фролов","Журавлев","Николаев","Крылов","Максимов","Сидоров","Осипов"};
    private static final String[] female_first_name = {"Анна","Алёна","Алевтина","Александра","Алина","Алла","Анастасия","Ангелина","Анжела","Анжелика","Антонида","Антонина","Анфиса","Арина","Валентина","Валерия","Варвара","Василиса","Вера","Вероника","Виктория","Галина","Дарья","Евгения","Екатерина","Елена","Елизавета","Жанна","Зинаида","Зоя","Ирина","Кира","Клавдия","Ксения","Лариса","Лидия","Любовь","Людмила","Маргарита","Марина","Мария","Надежда","Наталья","Нина","Оксана","Ольга","Раиса","Регина","Римма","Светлана","София","Таисия","Тамара","Татьяна","Ульяна","Юлия"};
    private static final String[] female_middle_name = {"Александровна","Алексеевна","Альбертовна","Анатольевна","Андреевна","Антоновна","Аркадьевна","Арсеньевна","Артёмовна","Борисовна","Вадимовна","Валентиновна","Валерьевна","Васильевна","Викторовна","Витальевна","Владимировна","Владиславовна","Вячеславовна","Геннадьевна","Георгиевна","Германовна","Григорьевна","Данииловна","Денисовна","Дмитриевна","Евгеньевна","Егоровна","Ивановна","Игнатьевна","Игоревна","Ильинична","Константиновна","Лаврентьевна","Леонидовна","Макаровна","Максимовна","Матвеевна","Михайловна","Никитична","Николаевна","Олеговна","Романовна","Семёновна","Сергеевна","Станиславовна","Степановна","Фёдоровна","Эдуардовна","Юрьевна","Ярославовна"};
    private static final String[] female_last_name = {"Смирнова","Иванова","Кузнецова","Попова","Соколова","Лебедева","Козлова","Новикова","Морозова","Петрова","Волкова","Соловьева","Васильева","Зайцева","Павлова","Семенова","Голубева","Виноградова","Богданова","Воробьева","Федорова","Михайлова","Беляева","Тарасова","Белова","Комарова","Орлова","Киселева","Макарова","Андреева","Ковалева","Ильина","Гусева","Титова","Кузьмина","Кудрявцева","Баранова","Куликова","Алексеева","Степанова","Яковлева","Сорокина","Сергеева","Романова","Захарова","Борисова","Королева","Герасимова","Пономарева","Григорьева","Лазарева","Медведева","Ершова","Никитина","Соболева","Рябова","Полякова","Цветкова","Данилова","Жукова","Фролова","Журавлева","Николаева","Крылова","Максимова","Сидорова","Осипова","Белоусова","Федотова","Дорофеева","Егорова","Матвеева","Боброва","Дмитриева","Калинина","Анисимова","Петухова","Антонова","Тимофеева","Никифорова","Веселова","Филиппова","Маркова","Большакова","Суханова","Миронова","Ширяева","Александрова","Коновалова","Шестакова","Казакова","Ефимова","Денисова","Громова","Фомина","Давыдова","Мельникова","Щербакова","Блинова","Колесникова","Карпова","Афанасьева","Власова","Маслова","Исакова","Тихонова","Аксенова","Гаврилова","Родионова","Котова","Горбунова","Кудряшова","Быкова","Зуева","Третьякова","Савельева","Панова","Рыбакова","Суворова","Абрамова","Воронова","Мухина","Архипова","Трофимова","Мартынова","Емельянова","Горшкова","Чернова","Овчинникова","Селезнева","Панфилова","Копылова","Михеева","Галкина","Назарова","Лобанова","Лукина","Белякова","Потапова","Некрасова","Хохлова","Жданова","Наумова","Шилова","Воронцова","Ермакова","Дроздова","Игнатьева","Савина","Логинова","Сафонова","Капустина","Кириллова","Моисеева","Елисеева","Кошелева","Костина","Горбачева","Орехова","Ефремова","Исаева","Евдокимова","Калашникова","Кабанова","Носкова","Юдина","Кулагина","Лапина","Прохорова","Нестерова","Харитонова","Агафонова","Муравьева","Ларионова","Федосеева","Зимина","Пахомова","Шубина","Игнатова","Филатова","Крюкова","Рогова","Кулакова","Терентьева","Молчанова","Владимирова","Артемьева","Гурьева","Зиновьева","Гришина","Кононова","Дементьева","Ситникова","Симонова","Мишина","Фадеева","Комиссарова","Мамонтова","Носова","Гуляева","Шарова","Устинова","Вишнякова","Евсеева","Лаврентьева","Брагина","Константинова","Корнилова","Авдеева","Зыкова","Бирюкова","Шарапова","Никонова","Щукина","Дьячкова","Одинцова","Сазонова","Якушева","Красильникова","Гордеева","Самойлова","Князева","Беспалова","Уварова","Шашкова","Бобылева","Доронина","Белозерова","Рожкова","Самсонова","Мясникова","Лихачева","Бурова","Сысоева","Фомичева","Русакова","Стрелкова","Гущина","Тетерина","Колобова","Субботина","Фокина","Блохина","Селиверстова","Пестова","Кондратьева","Силина","Меркушева","Лыткина","Турова"};
    private static final String[] Specialists = {"Аллерголог","Аллерголог-иммунолог","Ангионевролог","Ангиохирург","Андролог","Андролог-эндокринолог","Анестезиолог","Апитерапевт","Аритмолог","Ароматерапевт","Артролог","Бактериолог","Бальнеолог","Валеолог","Венеролог","Вертебролог","Вирусолог","Врач по спортивной медицине","Врач скорой помощи","Врач ультразвуковой диагностики","Врач функциональной диагностики","Врач ЭКО","Врач-эпилептолог","Гастроэнтеролог","Гематолог","Генетик","Гепатолог","Гериатр (геронтолог)","Гинеколог","Гинеколог-онколог","Гинеколог-перинатолог","Гинеколог-эндокринолог","Гирудотерапевт","Гистолог","Гомеопат","Дерматовенеролог","Дерматолог","Детский гинеколог","Детский невропатолог","Детский хирург","Диабетолог","Диетолог","Иглорефлексотерапевт","Иммунолог","Имплантолог","Инфекционист","Кардиолог","Кардиохирург","Кинезиолог","КЛД (лаборант)","Комбустиолог","Косметолог-дерматолог","Курортолог","Логопед","ЛФК-врач","Маммолог","Миколог","Микрохирург","Нарколог","Натуротерапевт","Невропатолог","Нейрохирург","Неонатолог","Нефролог","Окулист","Онколог","Онкоуролог","Ортопед","Остеопат","Отоларинголог (ЛОР)","Паразитолог","Пародонтолог","Педиатр","Пластический хирург","Подолог","Проктолог (колопроктолог)","Профпатолог","Психиатр","Психиатр-нарколог","Психоаналитик","Психолог","Психоневролог","Психотерапевт","Пульмонолог","Радиолог","Реабилитолог","Реаниматолог","Ревматолог (кардиоревматолог)","Рентгенолог","Рефлексотерапевт","Сексолог","Сексопатолог","Семейный врач","Сомнолог","Стоматолог","Стоматолог-имплантолог","Стоматолог-ортодонт","Стоматолог-ортопед","Стоматолог-терапевт","Стоматолог-хирург","Суггестолог","Судебно-медицинский эксперт","Сурдолог","Терапевт","Терапевт женской консультации","Токсиколог","Торакальный хирург","Травматолог","Трансплантолог","Трансфузиолог","Трихолог","Уролог","Фармаколог клинический","Физиотерапевт","Фитотерапевт","Флеболог","Фониатр","Фтизиатр","Химиотерапевт","Хирург","Челюстно-лицевой хирург","Эмбриолог","Эметолог","Эндокринолог","Эндоскопист","Эпидемиолог"};
    private static final String[] treatmentNameList = {"Обседование","Обследование сердечно-сосудистой системы","Приём врача","УЗИ брюшной полости","ЭКГ","УЗИ почек","МРТ головы","МРТ спины","МРТ брюшной полости","КТ головы","КТ спины",};
    private static final String[] treatmentDiscriptionList = {"Проведение обследования тела","Проведение полноценного обследования сердечно-сосудистой системы. Осмотр сердечной мышцы, сосудов головы.","Плановый приём у врача-специалиста","Проведение УЗИ брюшной полости","Проведение ЭКГ сердечной мышцы","Проведение УЗИ почек для обследования выделительной системы","Проведение МРТ с целью осмотра головного мозга.","Проведение МРТ с целью осмотра позвонков, в том числе спинного мозга.","Проведения МРТ брюшной полости с целью обследования.","Проведение компьютерной томографии с целью осмотра головы и головного мозга.","Проведение компьютерной томографии с целью осмотра спины, позвоночника и спинного мозга.",};


    private static final Random rn = new Random(System.currentTimeMillis());
    private static final Faker faker = new Faker(new Locale("ru"));

    public static String getName() {
        if (Math.abs(rn.nextInt()) % 2 == 0) {
            return male_first_name[Math.abs(rn.nextInt()) % male_first_name.length] + " " +
                    male_last_name[Math.abs(rn.nextInt()) % male_last_name.length] + " " +
                    male_middle_name[Math.abs(rn.nextInt()) % male_middle_name.length];
        } else {
            return female_first_name[Math.abs(rn.nextInt()) % female_first_name.length] + " " +
                    female_last_name[Math.abs(rn.nextInt()) % female_last_name.length] + " " +
                    female_middle_name[Math.abs(rn.nextInt()) % female_middle_name.length];
        }
    }
    public static Patient getPatient() {
        String fullname = getName();
        String[] tmp = fullname.split(" ");
        String name = tmp[0],
                fam = tmp[1],
                otc = tmp[2];
        return new Patient(fam,
                name,
                otc,
                faker.date().birthday(10, 25),
                faker.address().city() + " " + faker.address().streetAddress());
    }
    public static List<Patient> getPatient(int len) {
        List<Patient> ListOfPatient= new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            ListOfPatient.add(getPatient());
        }
        return ListOfPatient;
    }
    public static Doctor getDoctor() {
        String fullname = getName();
        String[] tmp = fullname.split(" ");
        String name = tmp[0];
        String fam = tmp[1];
        String otc = tmp[2];
        return new Doctor(fam + " " + name + " " + otc,
                Specialists[Math.abs(rn.nextInt()) % Specialists.length],
                faker.date().birthday(0, 5)
                );
    }
    public static List<Doctor> getDoctor(int len) {
        List<Doctor> ListOfDoctor = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            ListOfDoctor.add(getDoctor());
        }
        return ListOfDoctor;
    }

    public static TreatmentType getTreatmentType() {
        return getTreatmentType(1).get(0);
    }

    public static List<TreatmentType> getTreatmentType(int len) {
        List<TreatmentType> ListOfTreatment = new ArrayList<>();
        List<Integer> indexOfTreatment = new ArrayList<>();

        double maxCoast = 10000;
        double minCoast = 0;

        for (int i = 0; i < treatmentNameList.length; i++) {
            indexOfTreatment.add(i);
        }
        Collections.shuffle(indexOfTreatment);
        for (int i = 0; i < Math.min(indexOfTreatment.size(), len); ++i) {
            int index = indexOfTreatment.get(i);
            double dbPrice = minCoast + (maxCoast - minCoast) * rn.nextDouble();
            BigDecimal price = new BigDecimal(dbPrice);
            price = price.setScale(2, RoundingMode.DOWN);
            ListOfTreatment.add(new TreatmentType(treatmentNameList[index],
                    treatmentDiscriptionList[index], price));
        }

        return ListOfTreatment;
    }

    public static TreatmentSet getTreatmentSet(
                                                List<Integer> indexOfDoctors,
                                                List<Integer> indexOfPatients,
                                                List<Integer> indexOfTreatmentTypes) {

        Date dateNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -2);
        Date dateFrom = calendar.getTime();
        Date DateBegin = faker.date().between(dateFrom, dateNow);
        calendar.add(Calendar.YEAR, 4);
        Date date = calendar.getTime();
        Date DateEnd = faker.date().between(DateBegin, date);
        int TreatmentSetCount = Math.abs(rn.nextInt()) % 10;
        int TreatmentSetCountFact = Math.abs(rn.nextInt()) % (TreatmentSetCount + 1);
        int TreatmentTypeIdIndex = Math.abs(rn.nextInt()) % indexOfTreatmentTypes.size();
        int TreatmentTypeId = indexOfTreatmentTypes.get(TreatmentTypeIdIndex);
        int DoctorIdIndex = Math.abs(rn.nextInt()) % indexOfDoctors.size();
        int DoctorId = indexOfDoctors.get(DoctorIdIndex);
        int PatientIdIndex = Math.abs(rn.nextInt()) % indexOfPatients.size();
        int PatientId = indexOfPatients.get(PatientIdIndex);
        String[] tmp = {"A", "B", "C", "D", "E"};
        String TreatmentSetRoom = Integer.toString(1 + (Math.abs(rn.nextInt()) % 999)) +
                tmp[Math.abs(rn.nextInt()) % tmp.length];
        return new TreatmentSet(DoctorId,
                PatientId,
                DateBegin,
                DateEnd,
                TreatmentSetRoom,
                TreatmentSetCount,
                TreatmentSetCountFact,
                TreatmentTypeId);
    }


    public static List<TreatmentSet> getTreatmentSetByIndex(int len,
                                                     List<Integer> indexOfDoctors,
                                                     List<Integer> indexOfPatients,
                                                     List<Integer> indexOfTreatmentTypes) {
        List <TreatmentSet> ListOfTreatmentSet = new ArrayList<>();

        for (int i = 0; i < len; ++i) {
            ListOfTreatmentSet.add(getTreatmentSet(indexOfDoctors,
                    indexOfPatients,
                    indexOfTreatmentTypes));
        }
        return ListOfTreatmentSet;
    }
    public static List<TreatmentSet> getTreatmentSet(int len,
                                                     List<Doctor> doctors,
                                                     List<Patient> patients,
                                                     List<TreatmentType> treatmentTypes) {
        // List <TreatmentSet> ListOfTreatmentSet = new ArrayList<>();
        List<Integer> indexOfDoctors = new ArrayList<>();
        List<Integer> indexOfPatients = new ArrayList<>();
        List<Integer> indexOfTreatmentTypes = new ArrayList<>();

        doctors.forEach(
                (cur) -> indexOfDoctors.add(cur.getIntDoctorId())
        );
        patients.forEach(
                (cur) -> indexOfPatients.add(cur.getIntPatientId())
        );
        treatmentTypes.forEach(
                (cur) -> indexOfTreatmentTypes.add(cur.getIntTreatmentTypeId())
        );
        return getTreatmentSetByIndex(len,
                indexOfDoctors,
                indexOfPatients,
                indexOfTreatmentTypes);
    }

    public static TreatmentVisit getTreatmentVisit(List<TreatmentSet> treatmentSets) {
        int indexTreatmentSet = Math.abs(rn.nextInt()) % treatmentSets.size();
        TreatmentSet treatmentSet = treatmentSets.get(indexTreatmentSet);
        Date date = faker.date().between(treatmentSet.getDatDateBegin(),
                treatmentSet.getDatDateEnd());
        return new TreatmentVisit(treatmentSet.getIntTreatmentSetId(), date);

    }

    public static List<TreatmentVisit> getTreatmentVisit(int len,
                                                         List<TreatmentSet> treatmentSets) {
        List<TreatmentVisit> ListOfTreatmentVisit = new ArrayList<>();
        for(int i = 0; i < len; ++i)
            ListOfTreatmentVisit.add(getTreatmentVisit(treatmentSets));

        return ListOfTreatmentVisit;
    }



}
