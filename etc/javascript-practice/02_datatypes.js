// JSON
// Number
// 숫자를 나타내는 모든 자료형
// 정수, 실수, 무한대, 숫자 아님
// 정수
const length = 5
const size = 10

// 실수
const temperature = 36.5
const distance = 3.5
const floatPoint = 2.998e8

// 무한대
const infinite = Infinity
const infNegative = -Infinity

// Not a Number (NaN)
// 숫자가 표현되어야 하는 계산 또는 함수의 결과가 숫자가 아닐 때
const parseFail = parseInt('a')
const sqrtNegative = Math.sqrt(-1)
const calculateNan = 7 + NaN

// String
// 따옴표 쌍따옴표 둘다 문자열
const single = 'Hello JS'
const double = "Hello JS"
const concat = single + double
console.log(concat)
const paragraph = 'this \nis \nnewline'
console.log(paragraph)

// Backtick(`)을 사용해 Template Literal 을 만들 수 있다.
const name = 'Jeeho'
const message = `Hello, ${name}!!!
Welcome to JavaScript`
console.log(message)

// Object
// JSON 의 Object 를 부르는 말과 동일하다.
const person = {
    firstName: 'Jeeho',
    lastName: 'Park',
    'phone number': '010-1234-5678',
}
// 객체의 요소에 접근할때는 `.` 또는 `[]` 를 사용한다.
console.log(person.firstName)

console.log(person.lastName)
console.log(person['lastName'])
console.log(person['phone number'])

// Array
// 순서가 보장되는 배열
// 각 요소를 `,` 기준으로 분리해서 정의
const numbers = [1, 2, 3, 4, 5]
console.log(numbers[0])
console.log(numbers[2])
console.log(numbers[numbers.length - 1])