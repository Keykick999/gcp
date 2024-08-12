//api key
const apiKey = 'AIzaSyDtWXoaZG5kJLxYap_KigZvzNmrrq6FG28';

document.addEventListener('DOMContentLoaded', () => {
    fillInTypeFields();
})


//지역의 좌표값 구하기
function getCoordinate() {
    const url = 'https://maps.googleapis.com/maps/api/geocode/json?address=' + '신정4동&' + 'key=' + encodeURIComponent(apiKey);
    fetch(url, {
        method: 'GET'
    })
        .then(response => {
            if(response.ok) {
                return response.json();
            }
            throw new Error('network error');
        })
        .then(data => {
            //좌표 값
            const lat = data.results[0].geometry.location.lat;
            const lng = data.results[0].geometry.location.lng;
            const location = lat + ',' + lng;
            searchPlaces(location);
        })
        .catch(error => {
            console.error(error);
        });
}


//좌표값 이용해서 검색
function searchPlaces(location) {
    const type = document.getElementById('contents').value;
    const url = 'http://localhost:8090/api/proxy/places?location=' + encodeURIComponent(location) + '&radius=1500&type=' + encodeURIComponent(type);
    fetch(url, {
        method: 'GET'
    })
        .then(response => {
            if(response.ok) {
                return response.json();
            }
            throw new Error('network error');
        })
        .then(data => {
            const place_id = data.results[2].place_id;
            searchDetailsById(place_id);
        })
        .catch(error => {
            console.error(error);
        });
}


//place_id로 상세 정보 검색
function searchDetailsById(place_id) {
    const url = 'http://localhost:8090/api/proxy/details?place_id=' + encodeURIComponent(place_id);
    fetch(url, {
        method: 'GET'
    })
        .then(response => {
            if(response.ok) {
                return response.json();
            }
            throw new Error('network error');
        })
        .then(data => {
            //평점 값 없을 때는 0점으로 설정.
            let rating = data.result && data.result.rating != undefined ? data.result.rating : 0;    //평점
            let reviews = data.result && data.result.reviews != undefined ? data.result.reviews : 0; //리뷰
            let address_components = data.result && data.result.address_components != undefined ? data.result.address_components : 0; //주소
            let name = data.result && data.result.name ? data.result.name : 0; //상호명
            let contact = data.result && data.result.international_phone_number != undefined ? data.result.international_phone_number : 0;  //연락처


            // console.log(data);
            // console.log(address_components);
            // console.log(reviews);
            // console.log(name);
            // console.log(contact)
            // console.log(rating);
            loadResults(address_components, reviews, name, contact, rating);
        })
        .catch(error => {
            console.error(error);
        });
}


// 결과값 로딩
function loadResults(address, reviews, name, contact, rating) {
    const resultContainer = document.getElementById('resultContainer');
    resultContainer.innerHTML = '';

    // 테이블 생성
    const table = document.createElement('table');
    const thead = document.createElement('thead');
    const tbody = document.createElement('tbody');

    // 테이블 헤더 생성
    const headerRow = document.createElement('tr');
    headerRow.innerHTML = `
      <th>상호명</th>
      <th>주소</th>
      <th>평점</th>
      <th>리뷰</th>
      <th>연락처</th>
  `;
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // 테이블 바디 생성
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${name}</td>
      <td>${address}</td>
      <td>${rating || 'N/A'}</td>
      <td>${reviews[0].author + reviews[0].text || 'N/A'}</td>
      <td>${contact || 'N/A'}</td>
    `;
    tbody.appendChild(row);
    table.appendChild(tbody);
    resultContainer.appendChild(table);
}


//타입 공간 채우기
function fillInTypeFields() {
    const types = [
        { category: '식당 관련', value: 'restaurant', description: '일반 식당' },
        { category: '식당 관련', value: 'cafe', description: '카페' },
        { category: '식당 관련', value: 'bar', description: '바' },
        { category: '쇼핑 및 서비스', value: 'shopping_mall', description: '쇼핑몰' },
        { category: '쇼핑 및 서비스', value: 'store', description: '일반 상점' },
        { category: '쇼핑 및 서비스', value: 'bakery', description: '빵집' },
        { category: '쇼핑 및 서비스', value: 'grocery_or_supermarket', description: '슈퍼마켓' },
        { category: '오락 및 문화', value: 'movie_theater', description: '영화관' },
        { category: '오락 및 문화', value: 'night_club', description: '나이트 클럽' },
        { category: '교육 및 공공기관', value: 'library', description: '도서관' },
    ];

    const selectElement = document.getElementById('contents');

    types.forEach(type => {
        const option = document.createElement('option');
        option.value = type.value;
        option.textContent = `${type.description} (${type.value})`;
        selectElement.appendChild(option);
    });
}





















