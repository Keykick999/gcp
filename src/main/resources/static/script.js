let map;
let service;
let infowindow;

function initMap() {
    // 지도 초기 설정
    const center = new google.maps.LatLng(37.5665, 126.9780); // 서울 중심 좌표

    map = new google.maps.Map(document.getElementById('map'), {
        center: center,
        zoom: 15
    });

    infowindow = new google.maps.InfoWindow();

    const input = document.getElementById('search-input');
    const searchButton = document.getElementById('search-button');

    searchButton.addEventListener('click', () => {
        const query = input.value;
        performSearch(query);
    });
}

function performSearch(query) {
    const request = {
        query: query,
        fields: ['name', 'geometry', 'place_id', 'formatted_address', 'rating', 'user_ratings_total', 'formatted_phone_number', 'photos', 'reviews']
    };

    service = new google.maps.places.PlacesService(map);
    service.textSearch(request, (results, status) => {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            displayResults(results);
            map.setCenter(results[0].geometry.location);
        }
    });
}

function displayResults(results) {
    const resultContainer = document.getElementById('resultContainer');
    resultContainer.innerHTML = ''; // 기존 내용을 초기화

    results.forEach(result => {
        const resultDiv = document.createElement('div');
        const review = result.reviews && result.reviews.length > 0 ? result.reviews[0].text : 'No reviews available';
        resultDiv.innerHTML = `
            <h3>${result.name}</h3>
            <div class="stars" style="--rating: ${result.rating || 0};"></div>
            <p>${result.formatted_address}</p>
            <p>Reviews: ${result.user_ratings_total || 'N/A'}</p>
            <p>Phone: ${result.formatted_phone_number || 'N/A'}</p>
            <p>${review}</p>
        `;

        // 사진 추가
        if (result.photos && result.photos.length > 0) {
            const photoUrl = result.photos[0].getUrl({ maxWidth: 200, maxHeight: 200 });
            const img = document.createElement('img');
            img.src = photoUrl;
            resultDiv.appendChild(img);
        }

        resultContainer.appendChild(resultDiv);

        // 지도에 마커 추가
        const marker = new google.maps.Marker({
            position: result.geometry.location,
            map: map,
            title: result.name
        });

        marker.addListener('click', () => {
            infowindow.setContent(`<div><strong>${result.name}</strong><br>
                                   Rating: ${result.rating || 'N/A'}<br>
                                   ${result.formatted_address}</div>`);
            infowindow.open(map, marker);
        });
    });
}
