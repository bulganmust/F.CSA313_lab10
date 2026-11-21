# 

# \# Лаборатори №10 — Өгөгдлийн урсгалын тест (Data Flow Testing)

# 

# \*\*Оюутан:\*\* Ж.Булган

# \*\*Оюутны код:\*\* B232270062

# \*\*Хичээл:\*\* F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест

# \*\*Он:\*\* 2026

# \*\*GitHub:\*\* https://github.com/bulganmust/F.CSA313\_lab10

# 

# \---

# 

# \## 1. Лабораторийн зорилго

# 

# Энэхүү лабораторийн ажлын зорилго нь `DataFlowTargets.java` файлын `gcd()` болон `bonus()` функцүүдийн өгөгдлийн урсгалыг шинжилж, хувьсагчдын Definition (Def), Use, Definition–Use (DU) хос болон def-clear замуудыг тодорхойлоход оршино.

# 

# JUnit 5 ашиглан All-Defs болон All-DU-Pairs шалгуурт чиглэсэн тестүүд боловсруулж, Maven болон JaCoCo ашиглан тестийн үр дүн, кодын хамралтыг хэмжив.

# 

# \## 2. Ажлын орчин

# 

# \- Ubuntu (WSL2)

# \- OpenJDK 17.0.20.1

# \- Apache Maven 3.9.12

# \- JUnit 5.10.2

# \- JaCoCo 0.8.12

# \- Git, GitHub

# \- Graphviz

# 

# \### 2.1. Java хувилбар

# 

# Команд:

# 

# ```bash

# java -version

# ```

# 

# Бодит гаралт:

# 

# ```text

# openjdk version "17.0.20.1" 2026-08-18

# OpenJDK Runtime Environment (build 17.0.20.1+1-1-26.04-Ubuntu)

# OpenJDK 64-Bit Server VM (build 17.0.20.1+1-1-26.04-Ubuntu, mixed mode, sharing)

# ```

# 

# \### 2.2. Maven хувилбар

# 

# Команд:

# 

# ```bash

# mvn -version

# ```

# 

# Бодит гаралт:

# 

# ```text

# Apache Maven 3.9.12

# Maven home: /usr/share/maven

# Java version: 17.0.20.1, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64

# Default locale: en, platform encoding: UTF-8

# OS name: "linux", version: "6.18.33.1-microsoft-standard-wsl2", arch: "amd64", family: "unix"

# ```

# 

# \## 3. Төслийн бүтэц

# 

# ```text

# lab10-dataflow/

# ├── pom.xml

# ├── README.md

# ├── DU\_ANALYSIS.md

# ├── cfg-bonus.dot

# ├── cfg-bonus.png

# ├── results/

# │   ├── mvn-verify-alldefs.txt

# │   ├── jacoco-alldefs.csv

# │   ├── mvn-verify-final.txt

# │   ├── jacoco-final.csv

# │   ├── mvn-test-M1.txt

# │   ├── mvn-test-M2.txt

# │   ├── mvn-test-M2-kill.txt

# │   └── mvn-test-M3.txt

# └── src/

# &#x20;   ├── main/java/mn/edu/must/sqat/

# &#x20;   │   └── DataFlowTargets.java

# &#x20;   └── test/java/mn/edu/must/sqat/

# &#x20;       └── DataFlowTargetsTest.java

# ```

# 

# Mutation testing-ийн нотолгооны файлуудыг `results/` хавтсанд хадгалсан.

# 

# \## 4. Тест ажиллуулах

# 

# Бүх тест:

# 

# ```bash

# mvn clean verify

# ```

# 

# Зөвхөн All-Defs тестүүд:

# 

# ```bash

# mvn clean verify -Dgroups=alldefs

# ```

# 

# JaCoCo HTML тайлан:

# 

# `target/site/jacoco/index.html`

# 

# \---

# 

# \## A. Definition–Use шинжилгээ

# 

# \### A.1. Үндсэн ойлголтууд

# 

# \- \*\*Definition (Def):\*\* Хувьсагчид утга оноох.

# \- \*\*Use:\*\* Хувьсагчийн утгыг ашиглах.

# \- \*\*C-use:\*\* Тооцоолол, утга оноолт эсвэл `return` илэрхийлэлд ашиглах.

# \- \*\*P-use:\*\* Нөхцөл шалгах илэрхийлэлд ашиглах.

# \- \*\*Def-clear path:\*\* Definition-ээс use хүртэл тухайн хувьсагч дахин тодорхойлогдоогүй зам.

# \- \*\*All-Defs:\*\* Боломжтой definition бүрээс дор хаяж нэг use хүрэх def-clear замыг шалгах.

# \- \*\*All-DU-Pairs:\*\* Боломжтой definition–use хос бүрийг def-clear замаар шалгах.

# 

# \### A.2. gcd() функц

# 

# `gcd()` нь Евклидийн алгоритмаар хоёр тооны хамгийн их ерөнхий хуваагчийг олно.

# 

# \#### Хувьсагчдын Definition ба Use

# 

# | Хувьсагч | Definition мөр | C-use мөр | P-use мөр |

# |---|---|---|---|

# | x | 13, 17 | 16, 20 | — |

# | y | 13, 18 | 16, 17 | 15 |

# | tmp | 16 | 18 | — |

# 

# \#### gcd() функцийн DU хосууд

# 

# | ID | Хувьсагч | Def | Use | Төрөл | Def-clear зам | Тестийн ID |

# |---|---|---|---|---|---|---|

# | G01 | x | 13 | 16 | c-use | 13→15→16 | gcdNormal |

# | G02 | x | 13 | 20 | c-use | 13→15→20 | gcdSecondZero |

# | G03 | x | 17 | 16 | c-use | 17→18→15→16 | gcdNormal |

# | G04 | x | 17 | 20 | c-use | 17→18→15→20 | gcdNormal |

# | G05 | y | 13 | 15 | p-use | 13→15 | gcdNormal |

# | G06 | y | 13 | 16 | c-use | 13→15→16 | gcdNormal |

# | G07 | y | 13 | 17 | c-use | 13→15→16→17 | gcdNormal |

# | G08 | y | 18 | 15 | p-use | 18→15 | gcdNormal |

# | G09 | y | 18 | 16 | c-use | 18→15→16 | gcdNormal |

# | G10 | y | 18 | 17 | c-use | 18→15→16→17 | gcdNormal |

# | G11 | tmp | 16 | 18 | c-use | 16→17→18 | gcdNormal |

# 

# `gcdSecondZero()` нь давталтгүй замыг, `gcdNormal()` нь олон давталттай замыг шалгана.

# 

# \### A.3. bonus() функц

# 

# `bonus()` нь ирцийн мэдээллээр урамшууллын оноо тооцно.

# 

# \- Ирсэн өдрийн тоо `threshold`-д хүрвэл 5 оноо.

# \- Дараалсан ирцийн хамгийн урт цуваа 5 буюу түүнээс дээш бол нэмэлт 3 оноо.

# \- Буруу оролтод `IllegalArgumentException` шиднэ.

# 

# \#### Хувьсагчдын Definition ба Use

# 

# | Хувьсагч | Definition мөр | C-use мөр | P-use мөр |

# |---|---|---|---|

# | attendance | 31 | 38 | 32 |

# | threshold | 31 | — | 32, 50 |

# | a | 38 | — | 39 |

# | present | 35, 40 | 40 | 50 |

# | streak | 36, 41, 46 | 41, 43 | 42 |

# | best | 37, 43 | — | 42, 53 |

# | score | 49, 51, 54 | 54, 56 | — |

# 

# \#### bonus() функцийн DU хосууд

# 

# | ID | Хувьсагч | Def | Use | Төрөл | Def-clear зам | Тестийн ID |

# |---|---|---|---|---|---|---|

# | B01 | attendance | 31 | 32 | p-use | 31→32 | bonusNull |

# | B02 | attendance | 31 | 38 | c-use | 31→32→35→36→37→38 | bonusNormal |

# | B03 | threshold | 31 | 32 | p-use | 31→32 | bonusNegativeThreshold |

# | B04 | threshold | 31 | 50 | p-use | 31→32→35→36→37→38→49→50 | bonusEmpty |

# | B05 | a | 38 | 39 | p-use | 38→39 | bonusNormal |

# | B06 | present | 35 | 40 | c-use | 35→38→39→40 | bonusWithStreak |

# | B07 | present | 35 | 50 | p-use | 35→38→49→50 | bonusEmpty |

# | B08 | present | 40 | 40 | c-use | 40→38→39→40 | bonusWithStreak |

# | B09 | present | 40 | 50 | p-use | 40→41→42→43→38→49→50 | bonusNormal |

# | B10 | streak | 36 | 41 | c-use | 36→38→39→40→41 | bonusWithStreak |

# | B11 | streak | 41 | 41 | c-use | 41→42→38→39→40→41 | bonusWithStreak |

# | B12 | streak | 41 | 42 | p-use | 41→42 | bonusWithStreak |

# | B13 | streak | 41 | 43 | c-use | 41→42→43 | bonusWithStreak |

# | B14 | streak | 46 | 41 | c-use | 46→38→39→40→41 | bonusResetThenPresentDU |

# | B15 | best | 37 | 42 | p-use | 37→38→39→40→41→42 | bonusWithStreak |

# | B16 | best | 37 | 53 | p-use | 37→38→49→50→53 | bonusEmpty |

# | B17 | best | 43 | 42 | p-use | 43→38→39→40→41→42 | bonusWithStreak |

# | B18 | best | 43 | 53 | p-use | 43→38→49→50→53 | bonusWithStreak |

# | B19 | score | 49 | 54 | c-use | 49→50→53→54 | bonusStreakOnlyDU |

# | B20 | score | 49 | 56 | c-use | 49→50→53→56 | bonusNoReward |

# | B21 | score | 51 | 54 | c-use | 51→53→54 | bonusWithStreak |

# | B22 | score | 51 | 56 | c-use | 51→53→56 | bonusThresholdOnlyDU |

# | B23 | score | 54 | 56 | c-use | 54→56 | bonusWithStreak |

# 

# Энэ хүснэгт нь мөрийн түвшинд тодорхойлсон гол DU хосуудыг харуулна. `p-use`-ийн үнэн/худал ирмэгийг тус тусад нь тооцсон бүрэн хамралтын баталгаа биш.

# 

# \### A.4. 46-р мөрийн def-clear зам

# 

# `bonus()` функцийн 46-р мөр:

# 

# ```java

# streak = 0;

# ```

# 

# Энэ мөр нь тасалсан өдрийн дараа дараалсан ирцийн тоолуурыг дахин тодорхойлно.

# 

# \*\*Боломжтой DU хос: 46 → 41\*\*

# 

# `bonusResetThenPresentDU()` тестийн оролт:

# 

# ```java

# new int\[]{0, 1}

# ```

# 

# Гүйцэтгэлийн зам:

# 

# ```text

# 39(False) → 46 → 38 → 39(True) → 40 → 41

# ```

# 

# 46-р мөрд `streak = 0` болсон утгыг дараагийн давталтын 41-р мөрд ашиглана.

# 

# \*\*Биелэх боломжгүй DU хос: 46 → 42\*\*

# 

# ```text

# 46 → 38 → 39(True) → 40 → 41 → 42

# ```

# 

# 42-р мөрд хүрэхээс өмнө 41-р мөр `streak`-ийг дахин тодорхойлдог тул 46→42 def-clear зам байхгүй.

# 

# \*\*Биелэх боломжгүй DU хос: 46 → 43\*\*

# 

# 43-р мөрд хүрэхийн тулд мөн 41-р мөрөөр дамжина. Иймээс 46→43 def-clear зам боломжгүй.

# 

# Эдгээр биелэх боломжгүй хосуудыг боломжтой DU хосын хамралтад оруулахгүй.

# 

# \---

# 

# \## B. Тестийн хэрэгжүүлэлт

# 

# \### B.1. All-Defs тестүүд

# 

# JUnit 5-ийн `@Tag("alldefs")` тэмдэглэгээг ашиглан дараах 6 тестийг тусад нь ажиллуулсан.

# 

# | ID | Тест | Шалгах нөхцөл |

# |---|---|---|

# | AD01 | gcdNormal | Олон давталт |

# | AD02 | gcdSecondZero | Давталтгүй зам |

# | AD03 | bonusWithStreak | Дараалсан ирц |

# | AD04 | bonusNull | Null оролт |

# | AD05 | bonusNegativeThreshold | Сөрөг босго |

# | AD06 | bonusStreakResetsAndRestarts | Ирц тасарч дахин эхлэх |

# 

# Команд:

# 

# ```bash

# mvn clean verify -Dgroups=alldefs

# ```

# 

# Бодит үр дүн:

# 

# ```text

# Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

# BUILD SUCCESS

# ```

# 

# Нотолгооны файлууд:

# 

# \- `results/mvn-verify-alldefs.txt`

# \- `results/jacoco-alldefs.csv`

# 

# Эдгээр 6 тестийг All-Defs-ийн үндсэн definition-үүдийг шалгах зорилгоор сонгосон. Багцын хамгийн цөөн боломжит тестийн тоо мөн эсэхийг тусад нь батлаагүй.

# 

# \### B.2. All-DU-Pairs-д чиглэсэн тестүүд

# 

# Өмнөх 14 тест дээр 6 нэмэлт тест оруулж, нийт 20 тест болгосон.

# 

# | ID | Тест | Оролт | Хүлээгдэх үр дүн |

# |---|---|---|---|

# | DU01 | gcdMultipleIterationsDU | (48, 18) | 6 |

# | DU02 | gcdNoIterationDU | (9, 0) | 9 |

# | DU03 | bonusResetThenPresentDU | {0, 1}, 2 | 0 |

# | DU04 | bonusEndsWithAbsenceDU | {1, 0}, 2 | 0 |

# | DU05 | bonusStreakOnlyDU | {1,1,1,1,1}, 6 | 3 |

# | DU06 | bonusThresholdOnlyDU | {1,0,1}, 2 | 5 |

# 

# Нэмэлт тестүүд нь тодорхой DU замуудыг шалгахад чиглэсэн. Зарим нь өмнөх тестүүдтэй ижил замыг давтан шалгана.

# 

# \### B.3. CFG зураг

# 

# `bonus()` функцийн Control Flow Graph-ийг Graphviz ашиглан байгуулсан.

# 

# !\[bonus() Control Flow Graph](cfg-bonus.png)

# 

# CFG зурагт Java кодын мөрийн дугаар, нөхцөлийн салбарууд, давталтын замууд болон тестийн методын нэрсийг харуулсан.

# 

# Graphviz эх файл: `cfg-bonus.dot`

# 

# Зураг үүсгэх команд:

# 

# ```bash

# dot -Tpng cfg-bonus.dot -o cfg-bonus.png

# ```

# 

# \---

# 

# \## C. JUnit болон JaCoCo үр дүн

# 

# \### C.1. All-Defs шат

# 

# `results/mvn-verify-alldefs.txt`:

# 

# ```text

# Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

# BUILD SUCCESS

# ```

# 

# `results/jacoco-alldefs.csv`:

# 

# | Үзүүлэлт | Хамрагдсан | Хамралт |

# |---|---|---|

# | Instruction | 78/81 | 96.30% |

# | Branch | 14/16 | 87.50% |

# | Line | 23/24 | 95.83% |

# | Method | 2/3 | 66.67% |

# 

# \### C.2. Эцсийн 21 тест

# 

# Команд:

# 

# ```bash

# mvn clean verify

# ```

# 

# `results/mvn-verify-final.txt` файлын бодит гаралт:

# 

# ```text

# Tests run: 21, Failures: 0, Errors: 0, Skipped: 0

# BUILD SUCCESS

# ```

# 

# `results/jacoco-final.csv` файлын бодит хэмжилт:

# 

# | Үзүүлэлт | Хамрагдсан | Хамралт |

# |---|---|---|

# | Instruction | 78/81 | 96.30% |

# | Branch | 16/16 | 100% |

# | Line | 23/24 | 95.83% |

# | Method | 2/3 | 66.67% |

# 

# Нийт 21 тест бүгд амжилттай ажилласан.

# 

# `DataFlowTargets` классын автоматаар үүссэн constructor тестээр дуудагдаагүй тул нийт instruction, line, method coverage 100% биш байна.

# 

# \*\*Анхаарах зүйл:\*\* JaCoCo нь DU-pair coverage-ийг шууд хэмждэггүй. Тиймээс 100% branch coverage нь 100% All-DU-Pairs coverage гэсэн баталгаа биш.

# 

# \---

# 

# \## D. Fault Seeding / Mutation Testing

# 

# Mutation testing нь эх кодод зориуд жижиг өөрчлөлт оруулж, тестүүд уг алдааг илрүүлж чадаж байгаа эсэхийг шалгах арга юм.

# 

# \### Mutation Testing үр дүн

# 

# Эх кодод мутаци бүрийг тус тусад нь оруулж, тест ажиллуулан нотолгоог `results/` хавтсанд хадгалсан. Мутациудыг үндсэн эх кодод commit хийгээгүй.

# 

# | Мутаци | Өөрчлөлт | Үр дүн | Тестийн бодит үр дүн |

# |---|---|---|---|

# | M1 — Missing update | `streak = streak + 1;` мөрийг устгасан | \*\*KILLED\*\* | 20 тестээс 4 унасан |

# | M2 — Wrong variable | `best = streak;` → `best = present;` | \*\*KILLED\*\* | Эхний 20 тест бүгд давсан; нэмэлт тест оруулахад 21 тестээс 1 унасан (`expected: 5`, `actual: 8`) |

# | M3 — Dead definition | Давталтын дараа `int total = present + best;` нэмсэн | \*\*SURVIVED\*\* | 21 тест бүгд давсан |

# 

# \*\*M1:\*\* `bonusStreakOnlyDU`, `bonusStreakResetsAndRestarts`, `bonusWithStreak`, `bonusStreakWithoutThresholdReward` тестүүд унасан. `streak` нэмэгдэхгүй болсон тул дараалсан ирцийн 3 онооны урамшуулал тооцогдоогүй.

# 

# \*\*M2:\*\* Эхний 20 тест мутацийг илрүүлээгүй. Дараах шинэ тестийг нэмэхэд мутаци илэрсэн:

# 

# ```java

# @Test

# void bonusKillsM2WrongBest() {

# &#x20;   assertEquals(5, DataFlowTargets.bonus(

# &#x20;       new int\[]{1, 1, 0, 1, 1, 1, 1}, 6));

# }

# ```

# 

# Зөв хувилбарт `present = 6`, хамгийн урт дараалсан ирц `best = 4` тул үр дүн \*\*5\*\*. M2 хувилбарт `best = present` оноолтоос болж `best = 5` болж, буруу үр дүн \*\*8\*\* гарсан. Энэ нь `results/mvn-test-M2-kill.txt` файлд бүртгэгдсэн.

# 

# \*\*M3:\*\* `total` хувьсагчийг тодорхойлсон ч цааш ашиглаагүй тул гаралтад нөлөөлөөгүй. Иймээс 21 тест бүгд амжилттай болсон. Энэ нь мутацийн тестээр илрүүлэх боломжгүй, гаралтын хувьд эквивалент өөрчлөлт юм. IDE-ийн ашиглагдаагүй хувьсагчийн анхааруулгын бодит текстийг тусад нь бүртгээгүй.

# 

# \*\*Killed mutant\*\* гэдэг нь мутацитай код дээр дор хаяж нэг тест унасныг, \*\*survived mutant\*\* гэдэг нь тухайн тестийн багц мутацийг илрүүлээгүйг илэрхийлнэ.

# 

# Нотолгооны файлууд:

# 

# ```text

# results/mvn-test-M1.txt

# results/mvn-test-M2.txt

# results/mvn-test-M2-kill.txt

# results/mvn-test-M3.txt

# results/mvn-verify-final.txt

# results/jacoco-final.csv

# ```

# 

# Мутаци бүрийн дараа `DataFlowTargets.java` файлыг `git restore` ашиглан сэргээсэн. Эцсийн `git diff -- src/main/java/mn/edu/must/sqat/DataFlowTargets.java` хоосон гарсан тул үндсэн эх код өөрчлөгдөөгүй.

# 

# Эцсийн зөв код дээр \*\*21 тест амжилттай\*\*, branch coverage \*\*100%\*\*, line coverage \*\*95.83%\*\*, instruction coverage \*\*96.30%\*\* болсон.

# 

# \---

# 

# \## E. Git болон GitHub

# 

# Төслийн өөрчлөлтүүдийг Git ашиглан үе шаттай commit хийсэн.

# 

# \- Maven төслийн анхны тохиргоо

# \- JUnit тестүүд

# \- Definition–Use шинжилгээ

# \- Нэмэлт тестүүд

# \- All-Defs тест ба нотолгоо

# \- All-DU-Pairs-д чиглэсэн тестүүд

# \- CFG зураг

# \- M1–M3 мутацийн туршилт, нэмэлт JUnit тест болон нотолгооны файлууд

# 

# Repository:

# 

# https://github.com/bulganmust/F.CSA313\_lab10

# 

# \## F. Дүгнэлт

# 

# Лабораторийн ажлын хүрээнд `gcd()` болон `bonus()` функцүүдийн өгөгдлийн урсгалын шинжилгээ хийж, хувьсагчдын Definition, Use, DU хос болон def-clear замуудыг тодорхойлсон.

# 

# JUnit 5 ашиглан нийт 21 тест боловсруулсан. All-Defs шатанд 6 тестийг тусад нь ажиллуулж, бүх тест амжилттай болсон.

# 

# Эцсийн 21 тестийн JaCoCo хэмжилтээр instruction coverage 96.30%, branch coverage 100%, line coverage 95.83% гарсан.

# 

# `bonus()` функцийн 46-р мөрөөс 41-р мөрд хүрэх боломжтой def-clear замыг шалгаж, 46→42 болон 46→43 замууд яагаад боломжгүйг тайлбарласан.

# 

# Кодын хамралт өндөр байх нь бүх DU хос бүрэн шалгагдсан гэсэн үг биш тул өгөгдлийн урсгалын шинжилгээг JUnit болон JaCoCo хэмжилттэй хамтатган ашиглав.

# 

# Mutation testing-ээр M1 мутацийг 4 тест илрүүлсэн, M2 мутацийг шинэ JUnit тестээр илрүүлсэн, M3 мутаци тестийн багцаас амьд үлдсэн. Эх кодыг анхны төлөвт сэргээж, эцсийн 21 тест болон JaCoCo тайланг дахин ажиллуулан баталгаажуулсан.

# 

# \## G. AI ашигласан тухай

# 

# JUnit тестийн код, Definition–Use хүснэгтийн бүтэц, Maven команд, CFG зураг болон тайлангийн найруулгад ChatGPT-ийн тусламж ашигласан.

# 

# Тестүүдийг Ubuntu WSL2 орчинд ажиллуулж, үр дүнг Maven болон JaCoCo CSV тайлангаар баталгаажуулсан.

