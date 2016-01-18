App.controller("newGameController", function($scope, $http){
    $scope.activeTab = 1;

    $scope.setActiveTab = function(value) {
        $scope.activeTab = value;
    }
    $scope.isActiveTab = function(id) {
        return $scope.activeTab == id;
    }

    $scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };

});


