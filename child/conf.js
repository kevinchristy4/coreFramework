

var localConf = {
    framework: 'jasmine',
    capabilities: {
        chromeOptions: {
            // args: ['--headless', '--disable-gpu', '--disable-dev-shm-usage', '--no-sandbox', '--window-size=1920,1080']
        },
        browserName: 'chrome'
      },
    suites:{
        smokeTest:['tests.js'],
    },
    directConnect: true,
}



exports.config = localConf;