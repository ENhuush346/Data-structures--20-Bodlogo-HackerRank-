📊 Queries with Fixed Length
Сэдэв: Data Structures → Deque / Sliding Window
Хүндийн түвшин: Hard
Холбоос: Hackerrank – Queries with Fixed Length
Танд $n$ урттай массив arr[] өгөгдөнө. q ширхэг d утгатай хүсэлт байна. Хүсэлт бүрт та дараахийг олно:

d хэмжээтэй бүх тасархай хэсгүүд дундаас хамгийн бага дээд утгыг (minimum of max) ол.

Жишээ нь:
arr = [1, 3, 4, 2, 5, 3, 1], d = 3 → бүх 3 урттай хэсгүүд:

[1,3,4] → max=4

[3,4,2] → max=4

[4,2,5] → max=5

[2,5,3] → max=5

[5,3,1] → max=5
→ min of all max = 4

🧠 Шийдэл (Sliding Window + Deque)
d урттай цонхыг зүүнээс баруун тийш шилжүүлнэ.

Deque ашиглан тухайн цонхонд хамгийн их утга-г O(1)-д хадгална.

Дараа нь тэр max-уудын хамгийн багыг авна → min of max
