'use strict';

angular.module('core.resoner').service('ResonerService', function($http) {
		this.getDiagnose = (data) => {
			return $http.post('/api/resoner/', data);
		};
		
		this.getDisies = (data) => {
			return $http.post('/api/resoner/get-disies/', data);
		};
		
});