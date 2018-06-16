'use strict';

angular.module('get-symptoms').component('myGetSymptoms', {
	templateUrl: '/part/doctor/get-symptoms/get-symptoms.template.html',
	controller: function(DisiesService, UserService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'DOCTOR')
				$state.go('home');
		}, () => {
			$rootScope.user = null;
			$state.go('login')
		});
		
		DisiesService.getAll().then( (response) => {
			this.disies = response.data;
		});
	
	}
});