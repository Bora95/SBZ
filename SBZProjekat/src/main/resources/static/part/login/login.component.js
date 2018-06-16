'use strict';

angular.module('login').component('myLogin', {
	templateUrl: '/part/login/login.template.html',
	controller: function(UserService, $rootScope, $state) {
		UserService.get().then( (response) => {
			$rootScope.user = response.data;
			$state.go('home');
		});
		
		this.send = () => {
			UserService.login(this.user).then( (response) => {
					$rootScope.user = response.data;
					$state.go('home');
			}, () => {
				this.status = 'Wrong email/password.';
			});
		};
	}
});
