var app = angular.module('client1App', ['ngRoute', 'ngCookies']);

app.config(function ($routeProvider) {

    // Routes
    $routeProvider
        .when('/', {
            resolve: {
                "check": function ($location, $cookies, $window, $http) {
                    // Url parameters
                    var absUrl = $location.absUrl();
                    var authToken = $location.search().AuthToken;
                    var user = $location.search().User;

                    console.info("Requested Url: ", absUrl);
                    console.info("AuthToken: ", authToken);
                    console.info("User: ", user);

                    var applicationUrl = 'http://localhost:8084/#/';
                    var loginServerUrl = 'http://localhost:8080/#/';

                    if(user != null) {
                        $cookies.username = user;
                    }

                    if (authToken != null && authToken != "") {
                        $cookies.token = authToken;
                    }
                    if ($location.$$search.authToken) {
                        delete $location.$$search.authToken;
                        $location.$$compose();
                    }

                    if ($cookies.token != null && $cookies.token != "") {
                        var validateRequest = {
                            token: $cookies.token
                        };
                        var res = $http.post("/validate", validateRequest);
                        res.success(function (data, status, headers, config) {
                            if (data == null || data.success == null || data.success != true) {
                                $cookies.username = data.username;
                                $window.location.href = loginServerUrl + '?Origin=' + applicationUrl;
                            }
                        });
                    } else {
                        $window.location.href = loginServerUrl + '?Origin=' + applicationUrl;
                    }

                }
            },
            templateUrl: 'home.html',
            controller: 'client1Controller'
        }).when('/out', {
        resolve: {
            "check": function ($location, $cookies, $window, $http) {
                console.info("Logout Requested");
                $window.location.href = 'http://localhost:8080/#/?Out=true';
            }
        }
    }).otherwise({
        redirectTo: '/'
    });
});

app.controller('client1Controller', function ($scope, $location, $rootScope, $cookies) {
    if($cookies.username != null && $cookies.username != "") {
        $scope.username = $cookies.username;
    }
})