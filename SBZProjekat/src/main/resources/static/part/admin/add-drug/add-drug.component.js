'use strict';

angular.module('add-drug').component('myAddDrug', {
	templateUrl: '/part/admin/add-drug/add-drug.template.html',
	controller: function(UserService, SubstanceService, DrugService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'ADMIN')
				$state.go('login');
		}, () => { $state.go('login')});
		
		SubstanceService.get().then((response) => {
			this.substances = response.data;
		});
		
		this.send = () => {
			this.status = null;
			if(this.selected.length == 0)
				this.status = "Select substances"
			
			this.data.substances = [];
			for(var i=0;i<this.selected.length;i++)
				this.data.substances.push(this.selected[i].id);
				
			DrugService.add(this.data).then(() => {
				$state.go('home');
			}, (response) => {
				this.status = response.status;
			});
		};
	}
});