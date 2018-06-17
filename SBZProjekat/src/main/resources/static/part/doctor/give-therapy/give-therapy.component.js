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
		
		this.send = () => {
			this.status = null;
			if(!this.selectedRecord || !this.selectedRecord.originalObject.jmbg) {
				this.status = "Select medical record";
				return;
			}
			if(!this.selectedDisies || !this.selectedDisies.originalObject.id) {
				this.status = "Select diseas";
				return;
			}
			if(this.selectedDrugs.length == 0) {
				this.status = "Select drugs";
				return;
			}
			
			var drugs = [];
			for ( var i=0; i < this.selectedDrugs.length; i++) {
				drugs.push(this.selectedDrugs[i].id);
			}
			
			RecordService.addDiagnose({jmbg:this.selectedRecord.originalObject.jmbg, disies:this.selectedDisies.originalObject.id, drugs:drugs}).then( () => {
				$state.go('home');
			}, (response) => {
				if(response.status == 406)
					this.status = "Warnign can not give therapy because of alergies to: " + response.data.firstName;
				else
					this.status = response.status;
			});
		};
	}
});