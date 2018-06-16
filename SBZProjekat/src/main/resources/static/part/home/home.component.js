'use strict';

angular.module('home').component('myHome', {
	templateUrl: '/part/home/home.template.html',
	controller: function(UserService, $rootScope, $state) {
		UserService.get().then( () => {}, () => { $state.go('login')});
	}
});
