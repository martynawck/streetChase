var map;

App.directive('myMap', function() {
    var link = function(scope, element, attrs) {

        var mapOptions = {
            center: new google.maps.LatLng(51.783333, 19.466667),
            zoom: 6,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            scrollwheel: true,
            mapTypeControl: false,
            streetViewControl: false
        };

        function initMap() {
            if (map === void 0) {
                map = new google.maps.Map(element[0], mapOptions);
            }
        }
        initMap();

        google.maps.event.addListener(map, 'click', function (event) {
            // todo
        });
    };

    return {
        restrict: 'A',
        template: '<div id="gmaps"></div>',
        replace: true,
        link: link
    };
});

App.controller("StatsForGamePlayerController", function($scope, $http){
    $scope.activeTab = 0;

    $scope.setActiveTab = function(value) {
        $scope.activeTab = value;
    }

    $scope.isActiveTab = function(id) {
        return $scope.activeTab == id;
    }

    $scope.data = {}

    $scope.getRoute = function() {
        var url = window.location.pathname;
        $http.get(url)
            .success(function(data){
                $scope.data = data;
                $scope.drawRoute();
            }). error(function(){
                // todo
        })
    }

    $scope.drawRoute = function() {
        map.setCenter(new google.maps.LatLng(52.183432, 21.023503))
        map.setZoom(11);
        var flightPath = new google.maps.Polyline({
            path: $scope.data.route,
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 4
        });

        flightPath.setMap(map);
    }

    $scope.getRoute();
});











