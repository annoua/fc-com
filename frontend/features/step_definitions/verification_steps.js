const {Given, When, Then} = require('cucumber');
const url = require('../support/pages/urls').url;
const select = require('../support/pages/verification_page').select;


Given('eI am on the {string} page with the parameters {string}', async function (page, parameters) {
    await testController.navigateTo(url(page)+parameters);
});

Then('eI get redirected to the {string} page', async function (page) {
    await testController.wait(1000).expect(select('title').with({boundTestRun: testController}).innerText).eql(page);
});

Then('eThe {string} contains {string}', async function (field, expectedContent) {
    const inputField = select(field).with({boundTestRun: testController});
    await  testController.wait(2000).expect(await inputField.value).contains(expectedContent);
});

Then('eI get the message {string}', async function (msg) {
    const feedbackField = select('snackbar').with({boundTestRun: testController}).withText(msg);
    await  testController.expect(await feedbackField.exists).ok();
});

When('eI enter {string} in the {string}', async function (text, field) {
    const inputField = select(field);
    await testController.click(inputField)
        .selectText(inputField).pressKey('delete') //slow solution
        .typeText(inputField, text);
});

When('eI click on the {string}', async function (field) {
    const inputField = select(field);
    await testController.click(inputField);
});
