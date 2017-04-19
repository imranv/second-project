'use strict';
app.factory('FriendService', ['$http', '$q', '$rootScope', function($http, $q, $rootScope)
                   
   {
	console.log("FriendService....")
	
	var BASE_URL="http://localhost:8080/Collaboration"
		return{
		getMyFriends: function(){
			console.log("Getting friends from service")
			return $http.get(BASE_URL+'/getMyFriends/')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching Friends');
						return $q.reject(errResponse);
					});
			},
			
			acceptFriendRequest: function(id){
				console.log("accepting in friend")
				return $http.put(BASE_URL+'/acceptFriend/'+id)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while accepting friend user');
							return $q.reject(errResponse);
						});
			},
			
			rejectFriendRequest: function(id){
				console.log("rejecting in friend")
				return $http.put(BASE_URL+'/rejectFriend/'+id)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while rejecting friend user');
							return $q.reject(errResponse);
						});
			},
			
			sendFriendRequest: function(fid){
				return $http.post(BASE_URL+'/addfriend/'+fid)
				.then(
						function(response){
							
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating friend');
							return $q.reject(errResponse);
						});
			},
			updateFriendRequest:function(friend,id){
				return $http.put(BASE_URL+'/friend/'+id,friend)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating friend');
							return $q.reject(errResponse);
						});
			},
			
			deleteFriend: function(id){
				return $http,delete(BASE_URL+'/friend/'+id)
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while deleting friend');
							return $q.reject(errResponse);
						});
			},
			
			
			getMyFriendRequests: function(){
				return $http.get(BASE_URL+'/getMyFriendRequests/')
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while deleting friend');
							return $q.reject(errResponse);
						});
			},
			
			getMyFriends: function(){
				return $http.get(BASE_URL+'/getMyFriends/')
				.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while getting my friends');
							return $q.reject(errResponse);
						});
			},
	};	
	}])