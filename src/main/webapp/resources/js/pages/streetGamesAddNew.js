var map, infoWindow;
var markers = [];
var geocoder;
var tmpPoint = null, tmpMarker = null;
var points = [];

function getInfowindowContent(address) {
    return  '<div>'
        +   address + '<br/>'
        +   '<div class="btn" onclick="addPoint()">Dodaj punkt</div>'
        +   '</div>';
}

function setMarker(position) {
    geocoder.geocode({'location': position}, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK && results[0]) {
            tmpPoint = {'latLon': position, 'address': results[0].formatted_address};

            tmpMarker = new google.maps.Marker({
                position: position,
                map: map,
                icon: 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'
            });
            infoWindow.setContent(getInfowindowContent(results[0].formatted_address));
            infoWindow.open(map, tmpMarker);
        }
    });
}

function removeTmpPoint() {
    tmpPoint = null;
    if (tmpMarker != null)
        tmpMarker.setMap(null);
}

function addPoint() {
    var point = {
        'marker' : new google.maps.Marker({
                    position: tmpPoint.latLon,
                    map: map
                }),
        'address': tmpPoint.address,
        'latLon' : tmpPoint.latLon
    };
    points.push(point);

    removeTmpPoint();
}

App.directive('myMap', function() {
    var link = function(scope, element, attrs) {

        var mapOptions = {
            center: new google.maps.LatLng(50, 2),
            zoom: 4,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            scrollwheel: false
        };

        function initMap() {
            if (map === void 0) {
                map = new google.maps.Map(element[0], mapOptions);
            }
        }
        initMap();

        google.maps.event.addListener(map, 'click', function (event) {
            setMarker( new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()));
        });

        geocoder = new google.maps.Geocoder;
        infoWindow = new google.maps.InfoWindow();

        google.maps.event.addListener(infoWindow, 'closeclick', function() {
           removeTmpPoint();
        });
    };

    return {
        restrict: 'A',
        template: '<div id="gmaps"></div>',
        replace: true,
        link: link
    };
});

App.controller("newGameController", function($scope, $http){
    $scope.activeTab = 0;
    $scope.page = {points : []}

    $scope.setActiveTab = function(value) {
        $scope.activeTab = value;

        if (value == 1) {
            $scope.page.points = $scope.page.points.concat(points);
            points = [];
        }
    }
    $scope.isActiveTab = function(id) {
        return $scope.activeTab == id;
    }
});

