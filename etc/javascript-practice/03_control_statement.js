// 객체 Object는 Map과 유사
const person = {
    firstName: 'Jeeho',
    lastName: 'Park',
    'phone number': '010-1234-5678'
}
// for.. in (객체에서 순회)
for (const key in person) {
    console.log(person[key])
}

// 배열
const numbers = [10, 20, 30]
// for.. of (배열에서 순회)
for (const number of numbers) {
    console.log(numbers)
}