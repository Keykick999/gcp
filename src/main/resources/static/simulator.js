let map;
let markers = [];
let selectedPlaces = [];
const priceCategories = {
    'restaurant': [10000, 30000, 50000],
    'gym': [50000, 100000, 150000]
};

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 37.5665, lng: 126.9780 },
        zoom: 13
    });
    populatePlaceCategories();
}

document.getElementById('search-button').addEventListener('click', () => {
    const query = document.getElementById('address').value;
    const type = document.getElementById('contents').value;
    searchPlaces(query, type);
});

document.getElementById('address').addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
        event.preventDefault();
        document.getElementById('search-button').click();
    }
});

function handleInputChange() {
    const addressLabel = document.getElementById('address-label');
    addressLabel.textContent = "입력중";
}

function searchPlaces(query, type) {
    const geocoder = new google.maps.Geocoder();
    geocoder.geocode({ address: query }, (results, status) => {
        if (status === 'OK') {
            map.setCenter(results[0].geometry.location);
            clearMarkers();
            if (type) {
                fetchPlaces(results[0].geometry.location, type);
                fetchUserPlaces(query, type);
            } else {
                const selectedCategory = document.getElementById('categories').value;
                placeCategories[selectedCategory].forEach(placeType => {
                    fetchPlaces(results[0].geometry.location, placeType.type);
                    fetchUserPlaces(query, placeType.type);
                });
            }
            adjustLayout(true);
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}

function fetchPlaces(location, type) {
    const service = new google.maps.places.PlacesService(map);
    const request = {
        location: location,
        radius: '1000',
        type: [type]
    };
    service.nearbySearch(request, (results, status) => {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            const filteredResults = results.filter(place => place.price_level !== undefined);
            displayResults(filteredResults, type);
        } else {
            alert('Places search was not successful for the following reason: ' + status);
        }
    });
}

function fetchUserPlaces(query, type) {
    const userPlaces = JSON.parse(localStorage.getItem('userPlaces')) || [];
    const filteredUserPlaces = userPlaces.filter(place => place.type === type && place.name.includes(query));
    displayResults(filteredUserPlaces, type, true);
}

function clearMarkers() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
}

function getPriceText(priceLevel, type, isUserPlace = false) {
    if (isUserPlace) return `${priceLevel}원`;

    const priceThresholds = priceCategories[type] || [10000, 30000, 50000];
    switch (priceLevel) {
        case 0:
            return '무료';
        case 1:
            return `저렴함 (<= ${priceThresholds[0]}원)`;
        case 2:
            return `보통 (${priceThresholds[0]}원 - ${priceThresholds[1]}원)`;
        case 3:
            return `비쌈 (${priceThresholds[1]}원 - ${priceThresholds[2]}원)`;
        case 4:
            return `매우 비쌈 (>= ${priceThresholds[2]}원)`;
        default:
            return '가격 정보 없음';
    }
}


function displayResults(results, type, isUserPlace = false) {
    const resultContainer = document.getElementById('resultContainer');
    resultContainer.innerHTML = '';
    resultContainer.classList.add('active');

    results.forEach(place => {
        if (!isUserPlace) {
            const marker = new google.maps.Marker({
                position: place.geometry.location,
                map: map
            });
            markers.push(marker);

            const infowindow = new google.maps.InfoWindow({
                content: `<div><strong>${place.name}</strong><br>${place.vicinity}</div>`
            });

            marker.addListener('click', () => {
                infowindow.open(map, marker);
            });
        }

        const placeDiv = document.createElement('div');
        placeDiv.innerHTML = `
            <h3>${place.name} (${type})</h3>
            ${isUserPlace ? '' : `<div class="stars" style="--rating: ${place.rating || 0};"></div>`}
            ${isUserPlace ? '' : `<p>${place.vicinity}</p>`}
            <p>${getPriceText(place.price_level, type, isUserPlace)}</p>
            <button onclick="addToSelected('${place.name}', '${type}', ${isUserPlace ? place.price : place.price_level})">추가</button>
        `;
        resultContainer.appendChild(placeDiv);
    });
}

function addToSelected(name, type, price) {
    const selectedList = document.getElementById('selectedList');
    const listItem = document.createElement('li');
    listItem.textContent = `${name} (${type}) - ${price}원`;
    selectedList.appendChild(listItem);
    selectedPlaces.push({ name, type, price });
}

document.getElementById('undo-button').addEventListener('click', () => {
    if (selectedPlaces.length > 0) {
        selectedPlaces.pop();
        const selectedList = document.getElementById('selectedList');
        selectedList.removeChild(selectedList.lastChild);
    }
});

document.getElementById('view-results-button').addEventListener('click', () => {
    localStorage.setItem('selectedPlaces', JSON.stringify(selectedPlaces));
    window.location.href = '/result';
});

function handleInputFocus() {
    const label = document.querySelector('label[for="address"]');
    label.style.color = '#000';
    label.style.fontWeight = 'bold';
}

function handleInputBlur() {
    const label = document.querySelector('label[for="address"]');
    label.style.color = '#aaa';
    label.style.fontWeight = 'normal';
    label.textContent = '주소'; // 입력 중이 끝났을 때 원래 텍스트로 복구
}

function adjustLayout(isSearchCompleted) {
    const mapElement = document.getElementById('map');
    const resultContainer = document.getElementById('resultContainer');
    if (isSearchCompleted) {
        mapElement.classList.remove('full-width');
        mapElement.classList.add('half-width');
        resultContainer.classList.add('active');
    } else {
        mapElement.classList.remove('half-width');
        mapElement.classList.add('full-width');
        resultContainer.classList.remove('active');
    }
}

// 초기 로드 시 지도를 전체 너비로 설정
adjustLayout(false);
