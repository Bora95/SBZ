'use strict';

angular.module('get-diagnose').component('myGetDiagnose', {
	templateUrl: '/part/doctor/get-diagnose/get-diagnose.template.html',
	controller: function(ResonerService, SymptomService, UserService, RecordService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'DOCTOR')
				$state.go('home');
		}, () => {
			$rootScope.user = null;
			$state.go('login')
		});
		
		SymptomService.getAll().then( (response) => {
			this.symptoms = response.data;
		});
		
		RecordService.getAll().then( (response) => {
			this.records = response.data;
		});
		
		this.send = () => {
			this.status = null;
			if(!this.selectedRecord || !this.selectedRecord.originalObject.jmbg) {
				this.status = "Select medical record"
				return;
			}
			
			var symptoms = [];
			for ( var i=0; i < this.selectedSymptoms.length; i++) {
				symptoms.push(this.selectedSymptoms[i].id);
			}
			
			ResonerService.getDiagnose({jmbg:this.selectedRecord.originalObject.jmbg, type:this.type, symptoms:symptoms}).then( (response) => {
				this.diagnose = response.data;
				this.diagnoseStatus = response.data.name;
			}, (response) => {
				this.diagnose = null;
				this.diagnoseStatus = "No match found.";
			});
		};
		
		this.redirect = () => {
			this.status = null;
			if(!this.selectedRecord || !this.selectedRecord.originalObject.jmbg) {
				this.status = "Select medical record";
				return;
			}
			if(!this.diagnose) {
				this.status = "Get diagnose";
				return;
			}
			$state.go("home.give-therapy", {jmbg : this.selectedRecord.originalObject.jmbg, disies : this.diagnose.id});
		};
	}
});