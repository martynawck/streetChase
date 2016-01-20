App.controller("StatsController", function($scope, $http){

    $scope.games = [];
    $scope.visibleGame = -1;

    $scope.clickOnGame = function(id) {
        if ($scope.visibleGame == id)
            $scope.visibleGame = -1;
        else
            $scope.visibleGame = id;
    }

    $scope.shouldBeVisible = function(id) {
        return $scope.visibleGame == id;
    }

    $scope.getStats = function() {
        var url = "/streetChase/protected/stats";
        $http.get(url).success(function(data){
           $scope.games = data;
        });
    }

    $scope.getStats();
});