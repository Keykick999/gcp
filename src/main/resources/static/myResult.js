document.addEventListener('DOMContentLoaded', (event) => {
    const recordContainer = document.getElementById('record-container');

    fetch('/api/result')
        .then(response => {
            if(response.ok) {
                return response.json();
            }
            alert('로그인 후에 이용해주세요.');
            window.location.href = '/auth/login';
        })
        .then(data => {
            data.forEach(record => {
                // Create a container for each record set
                const recordSet = document.createElement('div');
                recordSet.classList.add('record-set');

                // Total price
                const totalPrice = document.createElement('div');
                totalPrice.classList.add('total-price');
                totalPrice.textContent = `총 가격 : ${record.totalPrice}`;
                recordSet.appendChild(totalPrice);

                // Places
                record.places.forEach(place => {
                    const placeItem = document.createElement('div');
                    placeItem.classList.add('place-item');
                    placeItem.textContent = `장소 : ${place}`;
                    recordSet.appendChild(placeItem);
                });

                // Append the record set to the main container
                recordContainer.appendChild(recordSet);
            });
        });
});
