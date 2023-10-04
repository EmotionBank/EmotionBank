import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
} from "chart.js";
import { Line } from "react-chartjs-2";
import { useGetUserReport } from "@/hooks/apiHooks/useGetUserReport";

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);
 

const Report = () => {

    const { getUserReportData } = useGetUserReport();
    console.log(getUserReportData.deposits);
    return (
        <div>
        </div>
    );
};

export default Report;
