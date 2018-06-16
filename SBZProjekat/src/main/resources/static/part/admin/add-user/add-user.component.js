'use strict';

angular.module('add-user').component('myAddUser', {
	templateUrl: '/part/admin/add-user/add-user.template.html',
	controller: function(UserService, $rootScope, $state) {
		UserService.get().then( (response) => {
			if(response.data.type != 'ADMIN')
				$state.go('login');
		}, () => { $state.go('login')});
		
		
		this.send = () => {
			UserService.add(this.user).then( () => {
				this.status = "Successfully added";
			}, (response) => {
				this.status = response.status;
			});
		};
	}
});
