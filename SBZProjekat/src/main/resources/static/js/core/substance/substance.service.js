'use strict';

angular.module('core.substance').service('SubstanceService', function($http) {
		this.get = () => {
			return $http.get('/api/substance/');
		};
		
		this.add = (data) => {
			return $http.post('/api/substance/', data);
		};
		
		this.delete = (id) => {
			return $http.delete(`/api/substance/${id}`);
		};
});