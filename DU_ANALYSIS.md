# Lab 10 – Data Flow Analysis

## 1. Зорилго

`DataFlowTargets.java` файлын `gcd()` болон `bonus()` функцүүдийн хувьсагчдын Definition (Def), Use (C-use, P-use) болон Def-Use холбоог шинжлэх.

## 2. gcd() функцийн DU шинжилгээ

| Хувьсагч | Definition (мөр) | Use (мөр) |
|---|---|---|
| x | 13, 17 | 16, 20 |
| y | 13, 18 | 15, 16, 17 |
| tmp | 16 | 18 |

### Тестийн замууд

- `gcd(7, 0)` – давталт 0 удаа.
- `gcd(6, 6)` – давталт 1 удаа.
- `gcd(12, 8)` – давталт олон удаа.
- `gcd(0, 5)` – эхний параметр 0.

## 3. bonus() функцийн DU шинжилгээ

| Хувьсагч | Definition (мөр) | Use (мөр) |
|---|---|---|
| attendance | 31 | 32, 38 |
| threshold | 31 | 32, 50 |
| a | 38 | 39 |
| present | 35, 40 | 40, 50 |
| streak | 36, 41, 46 | 41, 42 |
| best | 37, 43 | 42, 43, 53 |
| score | 49, 51, 54 | 54, 56 |

## 4. Coverage үр дүн

JUnit тестүүдийг JaCoCo ашиглан шалгасан.

| Үзүүлэлт | Coverage |
|---|---|
| gcd() Instruction Coverage | 100% |
| bonus() Instruction Coverage | 100% |
| Branch Coverage | 100% |
| Нийт Instruction Coverage | 96% |

Нийт Instruction Coverage 96% байгаа нь `DataFlowTargets()` constructor тестээр дуудагдаагүйтэй холбоотой.

**Тайлбар:** Branch coverage 100% байгаа нь бүх DU холбоо хамрагдсан гэсэн баталгаа биш. DU coverage-ийг тусад нь үнэлэх шаардлагатай.