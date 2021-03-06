'use strict';

app.controller('UserController',['$http','$scope','UserService','$location','$rootScope','$cookieStore',
       function($http,$scope,UserService,$location,$rootScope,$cookieStore){
	console.log("UserController...")
	var self = this;
	self.user = {
			id : '',
			name : '',
			password : '',
			contact : '',
			email : '',
			role : '',
			status : '',
			remarks : '',
			isOnline : '',
			errorMessage:'',
			errorCode:''
	};
	self.users = [];
	
	$scope.getform=false;
	$scope.getgroup=false;
	
	        
			
	        self.fetchAllUsers = function(){
				UserService.fetchAllUsers().then(function(d){
					self.users = d;
				}, function(errResponse){
					console.error('Error while fetching Users');
				});
			};
	
			self.createUser = function(user){
				console.log("createUser...")
				UserService
				   .createUser(user)
				   .then(self.fetchAllUsers,
						   alert('Thanks for registration'),
						function(errResponse)
						{
					console.error('Error while creating User');
				});
			};
			
			
	
			self.acceptUser =function (id){
				console.log('accepting the user');
				UserService.accept(id).then(function(d){
					self.users=d;
					self.fetchAllUsers();
					alert("user is Accepted")
				},
				  function(errresponse){
					console.log('Error while accepting user')
				})
			};
			
			
			
	
			self.rejectUser =function (id){
				console.log('rejecting the user');
				UserService.reject(id).then(function(d){
					self.users=d;
					self.fetchAllUsers();
					alert("user is rejected")
				},
				  function(errresponse){
					console.log('Error while rejecting user')
				})
			};
			
			self.userupdate = function(user)
			{
				
				self.updateUser(self.user);
				/*self.updateUser(self.user,user.id);*/
			}
			
			self.updateUser = function(user,id){
				console.log('updating the user');
				UserService.updateUser(user,id).then(self.fetchAllUsers,
						function(errResponse){
					console.error('Error while updating User');
				});
			};
	
			self.authenticate = function(user){
				console.log("authenticate in usercontroller")
				UserService.authenticate(user).then(
						function(d) {
							   
							self.user = d;
							console.log("user.errorcode: " + self.user.errorcode)
							
							 if(self.user.status=='R')
								   {
								 
								 alert("Your registration is not approved. Please contact Admin");
									   user.setErrorCode("404");
									   user.setErrorMessage("Your registration is not approved. Please contact Admin");
								   }
							 else if(self.user.errorcode == "404")
								{
								
								alert("Invalid Credentials. Please try again.")
								
								self.user.id = "";
								self.user.password = "";
								
								}
							     else
							     
							     {
							    	 console.log("Valid credentials. Navigating to home page.")
							    	 $rootScope.currentUser = {
							    		 
							    		 id : self.user.id,
	 	            						name : self.user.name,
	 	            						password : self.user.password,
	 	            						contact : self.user.contact,
	 	            						email : self.user.email,
	 	            						role : self.user.role,
	 	            						status : self.user.status,
	 	            						remarks :self.user.remarks,
	 	            						isOnline : self.user.isOnline
							    	 };
							    	 $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
							    	 
							    	 $cookieStore.put('currentUser', $rootScope.currentUser);
							    	 console.log('Role of logged in user '+$rootScope.currentUser.role);
							    	 $location.path("/");
							     }
						   },
							
							function(errResponse){
							console.error('Error while aunthenticate users');
						});
			};
				
			
			
			self.logout = function(){
				console.log('calling the method logout')
				$rootScope.currentUser={};
				$cookieStore.remove('currentUser');
				
				console.log('calling the method logout of User Service')
				UserService.logout()
				$location.path('/');
			}
			
					self.deleteUser = function(id){
						UserService.deleteUser(id).then(self.fetchAllUsers,
								function(errResponse){
							console.error('Error while deleting User');
						});
					};
					
				
					self.fetchAllUsers();	
				
				self.login= function(){
					{
						console.log('login validation??',self.user);
						self.authenticate(self.user);
					}
				};
				
				self.submit = function(){
					{
						console.log('saving new User', self.user);
						self.createUser(self.user);
					}
					self.reset();
				};
				
				
				
				self.usermyprofile = function(){
					{
						self.myprofile();
					}
				}
				/*self.usermyprofile();*/
				/*self.myprofile=function()
				{
					self.myprofile();
				}
				*/
				self.myprofile = function(){
					console.log("myProfile...")
					UserService.myprofile($rootScope.currentUser.id)
					.then(function(d){
						self.user=d;
						$location.path("/myprofile")
					},
					function(errResponse){
						console.error('Error while fetching profile.');
					})
				}
				
				 self.reset=function(){
		        	  console.log('resetting the form',self.user);
		        	  self.user={
		        			  id : '',
		        				name : '',
		        				password : '',
		        				contact : '',
		        				email : '',
		        				role : '',
		        				status : '',
		        				remarks : '',
		        				isOnline : '',
		        				errorMessage:'',
		        				errorCode:''
		        	  };
		        	  $scope.myForm.$setPristine();//reset form
		          };
		          
		          
				
				self.edit= function(id){
					console.log('id to be edited',id);
					for(var i = 0; i < self.users.length; i++){
						if (self.users[i].id === id){
							self.user = angular.copy(self.users[i]);
							break;
						}
					}
				};
				
				
				
				
				
				
				
}])