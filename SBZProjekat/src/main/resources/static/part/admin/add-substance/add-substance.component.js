'use strict';

angular.module('add-substance').component('myAddSubstance', {
	templateUrl: '/part/admin/add-substance/add-substance.template.html',
	controller: function(UserService, SubstanceService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'ADMIN')
				$state.go('login');
		}, () => { $state.go('login')});
		
		
		this.send = () => {
			SubstanceService.add(this.substance).then(() => {
				$state.go('home');
			}, (response) => {
				this.status = response.status;
			});
		};
	}
});
