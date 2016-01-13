function LocationController($scope, $location) {
    if($location.$$absUrl.lastIndexOf('/contacts') > 0){
        $scope.activeURL = 'contacts';
    } else{
        if($location.$$absUrl.lastIndexOf('/streetgames') > 0)
            $scope.activeURL = 'streetgames';
        else
            $scope.activeURL = 'home';
    }
}