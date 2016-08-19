var app = angular.module('loginApp', ['angular-md5', 'ngRoute', 'ngCookies']);

app.config(function ($routeProvider) {
    // Routes
    $routeProvider
        .when('/', {
            resolve: {
                "check": function ($location, $cookies, $window, $http) {
                    // Url parameters
                    var absUrl = $location.absUrl();
                    var out = $location.search().Out;
                    var origin = $location.search().Origin;

                    console.info("Requested Url: ", absUrl);
                    console.info("Out request: ", out);
                    console.info("Origin: ", origin);
                    if (out != null && out != "") {
                        var invalidateRequest = {
                            token: $cookies.token
                        };
                        var res = $http.post("/invalidate", invalidateRequest);
                        res.success(function (data, status, headers, config) {
                            if (data != null && data.success != null && data.success == true) {
                                // Token invalidated
                            }
                        });
                        $cookies.token = "";
                    }

                    if (origin != null && origin != "") {
                        $cookies.origin = origin;
                    }

                    // Validate current auth token
                    var authToken = $cookies.token;
                    if (authToken != null && authToken != "") {
                        var validateRequest = {
                            token: authToken
                        };

                        var res = $http.post("/validate", validateRequest);
                        res.success(function (data, status, headers, config) {
                            if (data != null && data.success != null && data.success == true) {
                                // Token validated

                                // Redirect to origin with token
                                if ($cookies.origin != null && $cookies.origin != "") {
                                    $window.location.href = $cookies.origin + '#/?AuthToken=' + $cookies.token + '&User=' + data.username;
                                } else {
                                    // Origin not found
                                    // Default redirect to client1
                                    $window.location.href = 'http://localhost:8084/#/?AuthToken=' + $cookies.token + '&User=' + data.username;
                                }
                            }
                        });
                    }

                    if (out != null && out != "") {
                        $window.location.href = 'http://localhost:8084/#/';
                    }
                }
            },
            templateUrl: 'login.html',
            controller: 'loginController'
        }).otherwise({
        redirectTo: '/'
    });
});

app.controller('loginController', function ($scope, $location, $rootScope, $cookies, $http, $window, md5) {
    // Credentials submit
    $scope.submit = function () {
        $cookies.token = "";

        if($scope.username == null || $scope.username == "" || $scope.password == null || $scope.password == "") {
            $scope.credentialMismatch = true;
        } else {
            var hash = md5.createHash($scope.password);
            var authRequest = {
                username: $scope.username,
                password: hash
            };

            var res = $http.post("/auth", authRequest);
            res.success(function (data, status, headers, config) {

                // New token gathered
                if (data.token != null && data.token != "") {
                    $cookies.token = data.token;

                    // Redirect to origin with token
                    if ($cookies.origin != undefined) {
                        $window.location.href = $cookies.origin + '#/?AuthToken=' + $cookies.token + '&User=' + data.username;;
                    } else {
                        // Origin not found
                        // Default redirect to client1
                        $window.location.href = 'http://localhost:8084/#/?AuthToken=' + $cookies.token + '&User=' + data.username;;
                    }
                } else {
                    $scope.credentialMismatch = true;
                }
            });
        }
    }
})