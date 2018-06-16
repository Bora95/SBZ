'use strict';

angular.module('get-disies').component('myGetDisies', {
	templateUrl: '/part/doctor/get-disies/get-disies.template.html',
	controller: function(ResonerService, SymptomService, UserService, $rootScope, $state) {
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
		
		this.send = () => {
			this.status = null;
			if(this.selectedSymptoms.length == 0) {
				this.disies = null;
				this.status = "No symptoms selected.";
				return;
			}
				
			var symptoms = [];
			for ( var i=0; i < this.selectedSymptoms.length; i++) {
				symptoms.push(this.selectedSymptoms[i].id);
			}
			
			ResonerService.getDisies(symptoms).then( (response) => {
				this.disies = response.data;
			}, () => {
				this.disies = null;
				this.status = "Nothing found";
			} );
		};
	}
});