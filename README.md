# ArrayWrapper

Создайте класс-обертку для массива, который бы делал: инициализацию массива с учетом
 начального размера; уменьшение размера массива при его переполнении;
добавление/удаление элементов массива (со сжатием массива); вычислял min, max, avg
значения элементов массива.
Доп условия: Массив должен быть строго определенного типа,
для этого создайте иерархию классов по вашему усмотрению и корень вашей иерархии
будет типом массива в обертке.
К примеру: есть базовый обьект Animal у которого есть методы: Integer getWeight()
Обертка должна использовать определенные в Base методы для вычисления min, max, avg значений
