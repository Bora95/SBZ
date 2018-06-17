'use strict';

angular.module('add-symptom').component('myAddSymptom', {
	templateUrl: '/part/admin/add-symptom/add-symptom.template.html',
	controller: function(UserService, SubstanceService, DrugService, SymptomService, DisiesService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'ADMIN')
				$state.go('login');
		}, () => { $state.go('login')});
		
		DrugService.getAll().then((response) => {
			this.drugs = response.data;
		});
		DisiesService.getAll().then((response) => {
			this.disies = response.data;
		});
		SymptomService.getAll().then((response) => {
			this.symptoms = response.data;
		});
		
		this.send = () => {
			this.data.drug = [];
			this.data.disies = [];
			this.data.symptom = [];
			
			for(var i = 0; i<this.selectedDrugs.length;i++)
				this.data.drug.push(this.selectedDrugs[i].id);
			
			for(var i = 0; i<this.selectedSymptoms.length;i++)
				this.data.symptom.push(this.selectedSymptoms[i].id);
			
			for(var i = 0; i<this.selectedDisies.length;i++)
				this.data.disies.push(this.selectedDisies[i].id);
			
			SymptomService.add(this.data).then(() => {
				$state.go('home');
			}, (response) => {
				this.status = response.status;
			});
		};
	}
});
