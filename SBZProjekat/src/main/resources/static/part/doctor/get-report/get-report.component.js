'use strict';

angular.module('get-report').component('myGetReport', {
	templateUrl: '/part/doctor/get-report/get-report.template.html',
	controller: function(ResonerService, UserService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'DOCTOR')
				$state.go('home');
		}, () => {
			$rootScope.user = null;
			$state.go('login')
		});
		
		this.getReport = () => {
			if(!this.reportType)
				return;
			ResonerService.getReport(this.reportType).then( (response) => {
				this.patients = response.data;
			}, (response) => {
				this.patients = null;
				if(response.status = 404)
					this.status = "No patients found";
				else
					this.status = response.status;
			});
		};
		
	}
});