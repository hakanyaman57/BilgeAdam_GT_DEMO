function fn() {
  var config = {};

  var env = karate.env;
  if (!env) {
    env = 'local';
  }
  config.env = env;

  var defaultUrls = {
    local: 'https://restful-booker.herokuapp.com',
    qa: 'https://restful-booker.herokuapp.com',
    dev: 'https://restful-booker.herokuapp.com',
  };

  config.parabankBaseUrl= "https://parabank.parasoft.com/parabank/services/bank"
  config.apiBaseUrl = karate.properties['api.baseUrl'] || defaultUrls[env] || defaultUrls.local;
  config.apiBaseUrl2= karate.properties['api.baseUrl2'] || defaultUrls[env] || defaultUrls.dev;
  config.bookerUsername = karate.properties['api.username'] || 'admin';
  config.bookerPassword = karate.properties['api.password'] || 'password123';

  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 10000);
  karate.configure('logPrettyRequest', true);
  karate.configure('logPrettyResponse', true);

  return config;
}
