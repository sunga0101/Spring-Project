/*
int a = 1;
int b = 2;
* */

/*
* let
* const
* var
* */

// let
// 데이터 형식을 따지지 않는 변수
// let 으로 선언합니다.
let foo = 'a let variable'
// let 으로 선언한 변수는 재할당이 가능합니다.
foo = 'let variables can be reallocated'
console.log(foo)
// 다른 타입으로 재할당도 되나 권장되지는 않음
foo = 20
console.log(foo)

// const
// 불변하는 변수
// const 로 선언합니다.
const foo = 'a const variable'
// const 로 선언한 변수는 재할당이 불가능합니다.
// java의 final과 비슷한 역할
// 상수 선언시도 많이 활용
const PI = Math.PI

// var
// var 으로 선언합니다.
// let 과 유사하게 동작
var baz = 'a var variable'
// let과 마찬가지로 재할당이 가능합니다.
baz = 'var can be reallocated to'
console.log(baz)

// 호이스팅 (hoisting)
console.log(hoisted)  // undefined 오류
let hoisted = 'let not hoisted variable'

// 아래쪽에 선언한 변수들을 코드 최상단에서 우선 선언하도록
// 코드가 변경되어, 실제 값의 할당 이전에 참조가 가능해짐
console.log(hoisted) //
var hoisted = 'var hoisted variable'