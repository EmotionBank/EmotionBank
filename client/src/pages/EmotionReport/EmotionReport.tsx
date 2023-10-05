import { Line, Doughnut } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  ArcElement,
} from 'chart.js';
import { useGetUserReport } from '@/hooks/apiHooks/useGetUserReport';
import * as S from '@/pages/EmotionReport/EmotionReport.style';
import { useState } from 'react';

ChartJS.register(ArcElement, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const EmotionReport = () => {
  const [toggle, setToggle] = useState(true);
  const { getUserReportData } = useGetUserReport();
  const lineLabels = getUserReportData!.balances.map(e => String(e.day) + '일');
  const depositsLabels = getUserReportData!.deposits.map(e => e.categoryName);
  const withdrawalsLabels = getUserReportData!.deposits.map(e => e.categoryName);

  const lineData = {
    labels: lineLabels,
    datasets: [
      {
        label: '일별 이용금액',
        data: getUserReportData.balances.map(e => e.amount),
        borderColor: 'rgb(255, 99, 132)',
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
      },
    ],
  };
  const depositsdata = {
    labels: depositsLabels,
    datasets: [
      {
        label: '# of Votes',
        data: getUserReportData.deposits.map(e => e.amount),
        backgroundColor: [
          'rgba(255, 99, 132, 0.6)',
          'rgba(54, 162, 235, 0.6)',
          'rgba(255, 206, 86, 0.6)',
          'rgba(75, 192, 192, 0.6)',
          'rgba(153, 102, 255, 0.6)',
          'rgba(255, 159, 64, 0.6)',
        ],

        borderWidth: 1,
      },
    ],
  };
  const withdrawalsdata = {
    labels: withdrawalsLabels,
    datasets: [
      {
        label: '# of Votes',
        data: getUserReportData.withdrawals.map(e => e.amount),
        backgroundColor: [
          'rgba(153, 102, 255, 0.6)',
          'rgba(255, 159, 64, 0.6)',
          'rgba(255, 99, 132, 0.6)',
          'rgba(54, 162, 235, 0.6)',
          'rgba(255, 206, 86, 0.6)',
          'rgba(75, 192, 192, 0.6)',
        ],

        borderWidth: 1,
      },
    ],
  };

  return (
    <S.EmotionReportWrapper>
      <S.SelectHeader>
        <S.SelectContainer>
          <S.ToggleButton $toggle={toggle} onClick={() => setToggle(true)}>
            전체
          </S.ToggleButton>
          <S.ToggleButton $toggle={!toggle} onClick={() => setToggle(false)}>
            입/출금
          </S.ToggleButton>
        </S.SelectContainer>
      </S.SelectHeader>
      {toggle ? (
        <S.ReportContainer>
          <Line data={lineData} />
          <S.ReportSpan>이번달 내역</S.ReportSpan>
        </S.ReportContainer>
      ) : (
        <>
          <S.ReportContainer>
            <Doughnut data={depositsdata} />
            <S.ReportSpan>입금</S.ReportSpan>
          </S.ReportContainer>
          <S.ReportContainer>
            <Doughnut data={withdrawalsdata} />
            <S.ReportSpan>출금</S.ReportSpan>
          </S.ReportContainer>
        </>
      )}
    </S.EmotionReportWrapper>
  );
};

export default EmotionReport;
