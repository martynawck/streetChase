function contactsController($scope, $http) {

    $scope.url = "/streetChase/protected/streetGames/";

    $scope.getGamesList = function () {
        var url = $scope.url;
        $scope.startDialogAjaxRequest();

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateContactButton = false;
            });
    }

    $scope.populateTable = function (data) {
        if (data.size() > 0) {
            $scope.state = 'list';

            $scope.page = {source: data};

            $scope.displayCreateContactButton = true;
        } else {
            $scope.state = 'noresult';
            $scope.displayCreateContactButton = true;
        }
    }

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');

        $scope.contact = {};
        $scope.errorOnSubmit = false;
        $scope.errorIllegalAccess = false;
        $scope.displayValidationError = false;
    }

    $scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {
        $scope.populateTable(data);
        $("#loadingModal").modal('hide');

        if(!isPagination){
            if(modalId){
                $scope.exit(modalId);
            }
        }

        $scope.lastAction = '';
    }

    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    }

    $scope.handleErrorInDialogs = function (status) {
        $("#loadingModal").modal('hide');
        $scope.state = $scope.previousState;

        // illegal access
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
        $scope.lastAction = '';
    }

    $scope.addSearchParametersIfNeeded = function(config, isPagination) {
        if(!config.params){
            config.params = {};
        }

        config.params.page = $scope.pageToGet;

        if($scope.searchFor){
            config.params.searchFor = $scope.searchFor;
        }
    }

    $scope.resetContact = function(){
        $scope.contact = {};
    };

    $scope.createContact = function (newContactForm) {
        if (!newContactForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'create';

        var url = $scope.url;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

        $scope.addSearchParametersIfNeeded(config, false);

        $scope.startDialogAjaxRequest();

        $http.post(url, $.param($scope.contact), config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#addContactsModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.selectedContact = function (contact) {
        var selectedContact = angular.copy(contact);
        $scope.contact = selectedContact;
    }

    $scope.updateContact = function (updateContactForm) {
        if (!updateContactForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'update';

        var url = $scope.url + $scope.contact.id;

        $scope.startDialogAjaxRequest();

        var config = {}

        $scope.addSearchParametersIfNeeded(config, false);

        $http.put(url, $scope.contact, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#updateContactsModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.searchContact = function (searchContactForm, isPagination) {
        if (!($scope.searchFor) && (!searchContactForm.$valid)) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'search';

        var url = $scope.url +  $scope.searchFor;

        $scope.startDialogAjaxRequest();

        var config = {};

        if($scope.searchFor){
            $scope.addSearchParametersIfNeeded(config, isPagination);
        }

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#searchContactsModal", isPagination);
                $scope.displaySearchMessage = true;
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.deleteContact = function () {
        $scope.lastAction = 'delete';

        var url = $scope.url + $scope.contact.id;

        $scope.startDialogAjaxRequest();

        var params = {searchFor: $scope.searchFor, page: $scope.pageToGet};

        $http({
            method: 'DELETE',
            url: url,
            params: params
        }).success(function (data) {
                $scope.resetContact();
                $scope.finishAjaxCallOnSuccess(data, "#deleteContactsModal", false);
            }).error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.resetSearch = function(){
        $scope.searchFor = "";
        $scope.pageToGet = 0;
        $scope.getContactList();
        $scope.displaySearchMessage = false;
    }

    $scope.getContactList();
}
