'use strict';

angular.module('core.disies').service('DisiesService', function($http) {
		this.getAll = () => {
			return $http.get('/api/disies/');
		};
		
		this.getOne = (id) => {
			return $http.get(`/api/disies/${id}`);
		};
		
		this.add = (data) => {
			return $http.post('/api/disies/', data);
		};
		
		this.delete = (id) => {
			return $http.delete(`/api/disies/${id}`);
		}
});