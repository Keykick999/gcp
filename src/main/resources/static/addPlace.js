document.getElementById('addPlaceForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const placeName = document.getElementById('placeName').value;
    const placeType = document.getElementById('placeType').value;
    const placePrice = document.getElementById('placePrice').value;

    const userPlaces = JSON.parse(localStorage.getItem('userPlaces')) || [];
    userPlaces.push({ name: placeName, type: placeType, price: placePrice });
    localStorage.setItem('userPlaces', JSON.stringify(userPlaces));
    alert('장소가 추가되었습니다.');
    window.location.href = '/simulator';
});