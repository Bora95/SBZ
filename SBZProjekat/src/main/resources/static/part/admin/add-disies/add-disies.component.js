'use strict';

angular.module('add-disies').component('myAddDisies', {
	templateUrl: '/part/admin/add-disies/add-disies.template.html',
	controller: function(UserService, DisiesService, SymptomService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'ADMIN')
				$state.go('login');
		}, () => { $state.go('login')});
		
		
		SymptomService.getAll().then((response) => {
			this.symptoms = response.data;
			this.symptomsS = response.data.slice();
		});
		
		SymptomService.getAll().then((response) => {
			this.symptomsS = response.data;
		});
		
		this.send = () => {
			this.status =null;
			if(this.data.type == 'TYPE3' && (this.selectedSymptoms.length < 2 || this.selectedSpecific.lengt == 0)) {
				this.status = "Select at least two symptoms and one specific symptom for this type of diseas.";
				return;
			}
			if(this.selectedSymptoms.length == 0){
				this.status = "Select symptoms.";
				return;
			}
			
			this.data.symptoms = [];
			this.data.specificSymptoms = [];
			
			for(var i = 0; i<this.selectedSymptoms.length;i++)
				this.data.symptoms.push(this.selectedSymptoms[i].id);
			
			for(var i = 0; i<this.selectedSpecific.length;i++)
				this.data.specificSymptoms.push(this.selectedSpecific[i].id);
			
			console.log(this.data);
			
			DisiesService.add(this.data).then(() => {
				$state.go('home');
			}, (response) => {
				this.status = response.status;
			});
		};
	}
});
