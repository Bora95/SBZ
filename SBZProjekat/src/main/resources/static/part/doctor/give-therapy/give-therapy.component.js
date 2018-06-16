'use strict';

angular.module('give-therapy').component('myGiveTherapy', {
	templateUrl: '/part/doctor/give-therapy/give-therapy.template.html',
	controller: function(ResonerService, DisiesService, UserService, RecordService, DrugService, $rootScope, $state, $stateParams) {
		UserService.get().then( (response) => {
			if(response.data.type != 'DOCTOR')
				$state.go('home');
		}, () => {
			$rootScope.user = null;
			$state.go('login')
		});
		
		DisiesService.getAll().then( (response) => {
			this.disies = response.data;
			for(var i=0;i<this.disies.length;i++) {
				if(this.disies[i].id == $stateParams.disies) {
					this.initDisies = this.disies[i];
					return;
				}
			}
		});
		
		RecordService.getAll().then( (response) => {
			this.records = response.data;
			for(var i=0;i<this.records.length;i++) {
				if(this.records[i].jmbg == $stateParams.jmbg) {
					this.initRecord = this.records[i];
					return;
				}
			}
		});
		
		DrugService.getAll().then( (response) => {
			this.drugs = response.data;
		});
	}
});