const placeCategories = {
    '식사': [
        { type: 'restaurant', name: '식당' },
        { type: 'cafe', name: '카페' },
        { type: 'bakery', name: '빵집' },
        { type: 'meal_delivery', name: '배달 음식' },
        { type: 'meal_takeaway', name: '테이크아웃 음식' }
    ],
    '정류장': [
        { type: 'bus_station', name: '버스 정류장' },
        { type: 'subway_station', name: '지하철역' },
        { type: 'train_station', name: '기차역' },
        { type: 'transit_station', name: '환승역' },
        { type: 'taxi_stand', name: '택시 승강장' }
    ],
    '술집': [
        { type: 'bar', name: '바' },
        { type: 'night_club', name: '나이트 클럽' },
        { type: 'liquor_store', name: '주류 상점' }
    ],
    '상점': [
        { type: 'store', name: '상점' },
        { type: 'clothing_store', name: '의류 상점' },
        { type: 'convenience_store', name: '편의점' },
        { type: 'department_store', name: '백화점' },
        { type: 'electronics_store', name: '전자 제품 상점' },
        { type: 'furniture_store', name: '가구 상점' },
        { type: 'hardware_store', name: '철물점' },
        { type: 'home_goods_store', name: '가정용품 상점' },
        { type: 'jewelry_store', name: '보석 상점' },
        { type: 'pet_store', name: '애완동물 상점' },
        { type: 'shoe_store', name: '신발 상점' },
        { type: 'shopping_mall', name: '쇼핑몰' },
        { type: 'supermarket', name: '슈퍼마켓' },
        { type: 'book_store', name: '서점' },
        { type: 'bicycle_store', name: '자전거 상점' }
    ],
    '놀거리': [
        { type: 'amusement_park', name: '놀이공원' },
        { type: 'aquarium', name: '수족관' },
        { type: 'art_gallery', name: '미술관' },
        { type: 'bowling_alley', name: '볼링장' },
        { type: 'casino', name: '카지노' },
        { type: 'museum', name: '박물관' },
        { type: 'park', name: '공원' },
        { type: 'stadium', name: '경기장' },
        { type: 'tourist_attraction', name: '관광 명소' },
        { type: 'zoo', name: '동물원' }
    ],
    '기타': [
        { type: 'accounting', name: '회계사' },
        { type: 'airport', name: '공항' },
        { type: 'beauty_salon', name: '미용실' },
        { type: 'campground', name: '캠핑장' },
        { type: 'car_dealer', name: '자동차 대리점' },
        { type: 'car_rental', name: '자동차 렌탈' },
        { type: 'car_repair', name: '자동차 수리' },
        { type: 'car_wash', name: '세차장' },
        { type: 'cemetery', name: '묘지' },
        { type: 'church', name: '교회' },
        { type: 'city_hall', name: '시청' },
        { type: 'courthouse', name: '법원' },
        { type: 'dentist', name: '치과' },
        { type: 'doctor', name: '의사' },
        { type: 'drugstore', name: '약국' },
        { type: 'electrician', name: '전기 기술자' },
        { type: 'embassy', name: '대사관' },
        { type: 'fire_station', name: '소방서' },
        { type: 'florist', name: '꽃집' },
        { type: 'funeral_home', name: '장례식장' },
        { type: 'gym', name: '헬스장' },
        { type: 'hair_care', name: '헤어케어' },
        { type: 'hindu_temple', name: '힌두교 사원' },
        { type: 'hospital', name: '병원' },
        { type: 'insurance_agency', name: '보험 대리점' },
        { type: 'laundry', name: '세탁소' },
        { type: 'lawyer', name: '변호사' },
        { type: 'library', name: '도서관' },
        { type: 'light_rail_station', name: '경전철역' },
        { type: 'local_government_office', name: '정부 사무소' },
        { type: 'locksmith', name: '자물쇠 기술자' },
        { type: 'lodging', name: '숙박' },
        { type: 'mosque', name: '모스크' },
        { type: 'movie_rental', name: '영화 대여점' },
        { type: 'movie_theater', name: '영화관' },
        { type: 'moving_company', name: '이삿짐 회사' },
        { type: 'painter', name: '화가' },
        { type: 'parking', name: '주차장' },
        { type: 'pharmacy', name: '약국' },
        { type: 'physiotherapist', name: '물리 치료사' },
        { type: 'plumber', name: '배관공' },
        { type: 'police', name: '경찰서' },
        { type: 'post_office', name: '우체국' },
        { type: 'primary_school', name: '초등학교' },
        { type: 'real_estate_agency', name: '부동산' },
        { type: 'roofing_contractor', name: '지붕 공사업자' },
        { type: 'rv_park', name: 'RV 파크' },
        { type: 'school', name: '학교' },
        { type: 'secondary_school', name: '중등학교' },
        { type: 'spa', name: '스파' },
        { type: 'storage', name: '창고' },
        { type: 'synagogue', name: '유대교 회당' },
        { type: 'travel_agency', name: '여행사' },
        { type: 'university', name: '대학교' },
        { type: 'veterinary_care', name: '수의사' }
    ]
};

function populatePlaceCategories() {
    const selectCategory = document.getElementById('categories');
    const selectType = document.getElementById('contents');

    for (const category in placeCategories) {
        const option = document.createElement('option');
        option.value = category;
        option.textContent = category;
        selectCategory.appendChild(option);
    }

    selectCategory.addEventListener('change', () => {
        const selectedCategory = selectCategory.value;
        selectType.innerHTML = '';
        placeCategories[selectedCategory].forEach(place => {
            const option = document.createElement('option');
            option.value = place.type;
            option.textContent = place.name;
            selectType.appendChild(option);
        });
    });
}

document.addEventListener('DOMContentLoaded', populatePlaceCategories);
