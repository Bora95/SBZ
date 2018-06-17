'use strict';

angular.module('add-record').component('myAddRecord', {
	templateUrl: '/part/doctor/add-record/add-record.template.html',
	controller: function(UserService, SubstanceService, DrugService, RecordService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'DOCTOR')
				$state.go('login');
		}, () => { $state.go('login')});
		
		DrugService.getAll().then((response) => {
			this.drugs = response.data;
		});
		
		SubstanceService.get().then((response) => {
			this.substances = response.data;
		});
		
		this.send = () => {
			
			this.data.alergicToSubstances = [];
			this.data.alergicToDrugs = [];
			
			for(var i=0;i<this.selectedSubstances.length;i++)
				this.data.alergicToSubstances.push(this.selectedSubstances[i].id);
			
			for(var i=0;i<this.selectedDrugs.length;i++)
				this.data.alergicToDrugs.push(this.selectedDrugs[i].id);
			
			RecordService.add(this.data).then(() => {
				$state.go('home');
			}, (response) => {
				this.status = response.status;
			});
		};
	}
});
