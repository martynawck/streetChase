App.controller("StatsController", function($scope, $http){

    $scope.games = [
        {name: "gra 123", id: 123, players: [{name: "gracz11", id: 11}, {name: "gracz22", id: 22}]},
        {name: "gra 345", id: 345, players: [{name: "gracz11", id: 11}, {name: "gracz22", id: 22}, {name: "gracz33", id: 33}]}
    ];
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
        var url = "/streetGames/stats";
        $http.get(url).success(function(data){
           $scope.games = data;
        });
    }

    $scope.getStats();
});