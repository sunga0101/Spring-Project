// 함수 선언식
// function 키워드를 이용해서 선언
function add (num1, num2){
    return num1 + num2
}

function addLog (num1, num2){
    console.log(num1 + num2)
}

console.log(add(10,20))
console.log(addLog(15,15))

// 함수 표현식
// 표현식 : 어떤 특정한 값으로 귀결되는 코드
// 변수를 선언하고, 해당 변수에 함수를 할당하는 방식
const subtract = function (num1,num2) {
    return num1-num2
}

// 함수 인자 전달하기
// 1. 인자 기본값
const greeting = function(name = 'Anonymous') {
    return `Hi, ${name}`
}
console.log(greeting('Jeeho'))
console.log(greeting())

// 2. 매개변수와 인자의 갯수 불일치 허용
const divide = function (num1, num2) {
    return num1 / num2
}

console.log(divide())
console.log(divide(1))
console.log(divide(1, 2))
console.log(divide(1, 2, 3))
