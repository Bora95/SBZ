'use strict';

angular.module('sbz')
	.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state({
				name: 'login',
				url: '/',
				component: 'myLogin'
			})
			.state({
				name: 'home',
				url: '/home',
				component: 'myHome'
			})
			.state({
				name: 'home.add-user',
				url: '/home/add-user',
				component: 'myAddUser'
			})
			.state({
				name: 'home.get-diagnose',
				url: '/home/get-diagnose',
				component: 'myGetDiagnose'
			})
			.state({
				name: 'home.get-disies',
				url: '/home/get-disies',
				component: 'myGetDisies'
			})
			.state({
				name: 'home.get-symptoms',
				url: '/home/get-symptoms',
				component: 'myGetSymptoms'
			})
			.state({
				name: 'home.give-therapy',
				url: '/home/give-therapy/',
				component: 'myGiveTherapy',
				params: { 
					jmbg: null, 
					disies : null	
				}
			})
			.state({
				name: 'error',
				url: '/error',
				template: '<h1>Error 404</h1>'
			});

		$urlRouterProvider
			.when('', '/')
			.otherwise('/error');
	})
	.run(function($rootScope, UserService) {
		UserService.get().then( (response) => {
				$rootScope.user = response.data;
		}, () => {
			$rootScope.user = null;
		});
	});
