'use strict';

angular.module('core.drug').service('DrugService', function($http) {
		this.getAll = () => {
			return $http.get('/api/drug/');
		};
		
		this.add = (data) => {
			return $http.post('/api/drug/', data);
		};
		
		this.delete = (id) => {
			return $http.delete(`/api/drug/${id}`);
		}
});