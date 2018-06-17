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
				name: 'home.add-substance',
				url: '/home/add-substance',
				component: 'myAddSubstance'
			})
			.state({
				name: 'home.add-drug',
				url: '/home/add-drug',
				component: 'myAddDrug'
			})
			.state({
				name: 'home.add-symptom',
				url: '/home/add-symptom',
				component: 'myAddSymptom'
			})
			.state({
				name: 'home.add-disies',
				url: '/home/add-diseas',
				component: 'myAddDisies'
			}).state({
				name: 'home.add-record',
				url: '/home/add-record',
				component: 'myAddRecord'
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
				name: 'home.get-report',
				url: '/home/get-report',
				component: 'myGetReport'
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
