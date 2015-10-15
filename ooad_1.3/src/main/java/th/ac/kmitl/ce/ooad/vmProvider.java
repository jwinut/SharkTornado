package th.ac.kmitl.ce.ooad;

/**
 * Created by Nut on 10/14/2015.
 */
public class vmProvider implements Provider {

    private static vmProvider vmprovider = new vmProvider();

    private vmProvider(){
    }

    public static vmProvider getInstance(){
        return vmprovider;
    }

    @Override
    public Cloud[] getClouds(Account user) {
        return new Cloud[0];
    }

    @Override
    public Plan[] getPlanByUser(Account user) {
        return new Plan[0];
    }

    public Plan[] getPlanByCloudAccount(CloudAccount account) {
        switch (account.getCloudProv()) {
            case GOOGLE:
                genPlan();

                break;

            case AMAZON:
                break;

            case DIGITAL_OCEAN:
                break;

            case AZURE:
                break;

            case VMWARE:
                break;
        }
    }

    @Override
    public Plan[] getPlanByCloudProv(Account user, String cloudProv) {
        return new Plan[0];
    }

    @Override
    public void changePlan(Account user, Plan newPlan) {

    }
}
