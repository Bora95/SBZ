'use strict';

angular.module('core.user').service('UserService', function($http) {
		this.get = () => {
			return $http.get('/api/user/');
		};
		this.getAll = () => {
			return $http.get('/api/user/all');
		};
		
		this.add = (data) => {
			return $http.post('/api/user/', data);
		};
		
		this.login = (data) => {
			return $http.put('/api/user/', data);
		};
		
		this.logout = () => {
			return $http.delete('/api/user/');
		};
		
		this.delete = (id) => {
			return $http.delete(`/api/user/delete/${id}`);
		}
		
});
